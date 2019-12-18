package pl.wedel.szzti.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.web.ItemSearchParameters;

@Slf4j
public class ItemRepositoryImpl implements SearchItemRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Page<Item> search(ItemSearchParameters searchParameters, Pageable pageable) {
    log.debug("Starting builder query");
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Item> query = builder.createQuery(Item.class);
    Root<Item> root = query.from(Item.class);
    root.fetch("rental", JoinType.LEFT);
    root.fetch("parent", JoinType.LEFT);
    query.select(root);

    applyFilters(builder, root, query, searchParameters);

    List<Item> list = entityManager.createQuery(query)
        .setFirstResult(pageable.getPageNumber() * pageable
            .getPageSize())
        .setMaxResults(pageable.getPageSize()).getResultList();
    return PageableExecutionUtils.getPage(list, pageable, () -> getCountForItems(searchParameters));
  }

  private Long getCountForItems(ItemSearchParameters searchParameters) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    Root<Item> root = countQuery.from(Item.class);
    root.join("rental", JoinType.LEFT);
    root.join("parent", JoinType.LEFT);
    countQuery.select(criteriaBuilder.count(root));

    applyFilters(criteriaBuilder, root, countQuery, searchParameters);

    return entityManager.createQuery(countQuery).getSingleResult();
  }

  private void applyFilters(CriteriaBuilder builder, Root<Item> root, CriteriaQuery criteriaQuery,
      ItemSearchParameters searchParameters) {

    List<Predicate> whereas = new ArrayList<>();
    if (searchParameters.containsKey("code")) {
      log.debug("Added where with code: {}", searchParameters.getCode());
      whereas.add(
          builder.and(builder.like(root.get("inventoryCode"),
              "%" + searchParameters.getCode() + "%")));
    }
    if (searchParameters.containsKey("skipRented")) {
      whereas.add(
          builder.and(builder.isNull(root.join("rental", JoinType.LEFT).get("id"))));
    }

    if (searchParameters.containsKey("skipNotRented")) {
      whereas.add(
          builder.and(builder.isNotNull(root.join("rental", JoinType.LEFT).get("id"))));
    }

    if (whereas.size() > 0) {
      Predicate[] whereasArray = new Predicate[whereas.size()];
      whereas.toArray(whereasArray);
      criteriaQuery.where(whereasArray);
    }
  }
}
