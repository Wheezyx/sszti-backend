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
import pl.wedel.szzti.domain.Rental;
import pl.wedel.szzti.web.RentalSearchParameters;

@Slf4j
public class RentalRepositoryImpl implements SearchRentalRepository {

  private static final String ITEM_ID = "itemId";
  private static final String PLACE_ID = "placeId";
  private static final String RENTER_ID = "renterId";


  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Page<Rental> search(RentalSearchParameters searchParameters, Pageable pageable) {
    log.debug("Starting builder query");
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Rental> query = builder.createQuery(Rental.class);
    Root<Rental> root = query.from(Rental.class);
    query.select(root);
    root.fetch("item", JoinType.LEFT);
    root.fetch("renter", JoinType.LEFT);
    root.fetch("place", JoinType.LEFT);
    List<Predicate> whereas = new ArrayList<>();

    if (searchParameters.containsKey(ITEM_ID)) {
      log.debug("Added where with item id: {}", searchParameters.getItemId());
      whereas.add(
          builder.and(builder.equal(root.join("item").get("id"),
              searchParameters.getItemId())));
    }

    if (searchParameters.containsKey(RENTER_ID)) {
      log.debug("Added where with renter id: {}", searchParameters.getRenterId());
      whereas.add(
          builder.and(builder.equal(root.join("renter").get("id"),
              searchParameters.getItemId())));
    }

    if (searchParameters.containsKey(PLACE_ID)) {
      log.debug("Added where with place id: {}", searchParameters.getPlaceId());
      whereas.add(
          builder.and(builder.equal(root.join("place").get("id"),
              searchParameters.getItemId())));
    }

    Predicate[] whereasArray = new Predicate[whereas.size()];
    whereas.toArray(whereasArray);
    query.where(whereasArray);
    List<Rental> list = entityManager.createQuery(query)
        .setFirstResult(pageable.getPageNumber() * pageable
            .getPageSize())
        .setMaxResults(pageable.getPageSize()).getResultList();
    return PageableExecutionUtils.getPage(list, pageable, () -> getCountForRentals(whereas));
  }

  private Long getCountForRentals(List<Predicate> whereas) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    CriteriaQuery<Long> countQuery = criteriaBuilder
        .createQuery(Long.class);
    countQuery.select(criteriaBuilder.count(
        countQuery.from(Rental.class)));
    if (whereas.size() > 0) {
      Predicate[] whereasArray = new Predicate[whereas.size()];
      whereas.toArray(whereasArray);
      countQuery.where(whereasArray);
    }
    Long count = entityManager.createQuery(countQuery)
        .getSingleResult();

    return count;
  }

}
