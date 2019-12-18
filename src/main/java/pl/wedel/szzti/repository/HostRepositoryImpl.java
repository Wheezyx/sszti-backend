package pl.wedel.szzti.repository;

import static pl.wedel.szzti.web.HostSearchParameters.CONNECTION_NAME;
import static pl.wedel.szzti.web.HostSearchParameters.CONNECTION_NUMBER;
import static pl.wedel.szzti.web.HostSearchParameters.INVENTORY_CODE;
import static pl.wedel.szzti.web.HostSearchParameters.IP;
import static pl.wedel.szzti.web.HostSearchParameters.MAC;
import static pl.wedel.szzti.web.HostSearchParameters.NAME;
import static pl.wedel.szzti.web.HostSearchParameters.PATCH_PANEL;
import static pl.wedel.szzti.web.HostSearchParameters.PLACE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
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
import pl.wedel.szzti.domain.Host;
import pl.wedel.szzti.web.HostSearchParameters;

@Slf4j
public class HostRepositoryImpl implements SearchHostRepository {

  private static final List<String> connectionInterfaceParameters = List.of(IP,
      MAC, PATCH_PANEL, CONNECTION_NAME, CONNECTION_NUMBER);

  private static final List<String> hostParameters = List.of(NAME, PLACE, INVENTORY_CODE);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Page<Host> search(HostSearchParameters searchParameters, Pageable pageable) {
    log.debug("Starting builder query");
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Host> query = builder.createQuery(Host.class);
    Root<Host> root = query.from(Host.class);
    query.select(root);
    //root.fetch("connectionInterfaces", JoinType.LEFT);
    applyFilters(searchParameters, builder, query, root);

    List<Host> list = entityManager.createQuery(query)
        .setFirstResult(pageable.getPageNumber() * pageable
            .getPageSize())
        .setMaxResults(pageable.getPageSize()).getResultList();

    return PageableExecutionUtils
        .getPage(list, pageable, () -> getCountForRentals(searchParameters));
  }

  private Long getCountForRentals(HostSearchParameters searchParameters) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> countQuery = builder
        .createQuery(Long.class);
    Root<Host> root = countQuery.from(Host.class);
    root.join("connectionInterfaces", JoinType.LEFT);
    countQuery.select(builder.count(root));
    applyFilters(searchParameters, builder, countQuery, root);

    return entityManager.createQuery(countQuery)
        .getSingleResult();
  }

  private void applyFilters(HostSearchParameters searchParameters, CriteriaBuilder builder,
      CriteriaQuery query, Root<Host> root) {
    List<Predicate> whereas = new ArrayList<>();

    searchParameters.getQueryParams().entrySet()
        .stream()
        .filter(stringObjectEntry -> containsParameter(stringObjectEntry, hostParameters))
        .forEach(stringObjectEntry -> applyHostFilter(builder, root, whereas, stringObjectEntry));

    searchParameters.getQueryParams().entrySet()
        .stream()
        .filter(stringObjectEntry ->
            containsParameter(stringObjectEntry, connectionInterfaceParameters))
        .forEach(stringObjectEntry ->
            applyConnectionInterfaceFilter(builder, root, whereas, stringObjectEntry));

    if (whereas.size() > 0) {
      Predicate[] whereasArray = new Predicate[whereas.size()];
      whereas.toArray(whereasArray);
      query.where(whereasArray);
    }
  }

  private boolean containsParameter(Entry<String, Object> stringObjectEntry,
      List<String> connectionInterfaceParameters) {
    return connectionInterfaceParameters.contains(stringObjectEntry.getKey());
  }

  private void applyConnectionInterfaceFilter(CriteriaBuilder builder, Root<Host> root,
      List<Predicate> whereas, Entry<String, Object> stringObjectEntry) {
    log.debug("Added where with: {}", stringObjectEntry.getValue());
    whereas.add(
        builder.and(
            builder.like(root.join("connectionInterfaces").get(stringObjectEntry.getKey()),
                "%" + stringObjectEntry.getValue() + "%")));
  }

  private void applyHostFilter(CriteriaBuilder builder, Root<Host> root, List<Predicate> whereas,
      Entry<String, Object> stringObjectEntry) {
    log.debug("Added where with: {}", stringObjectEntry.getValue());
    whereas.add(
        builder.and(builder.like(root.get(stringObjectEntry.getKey()),
            "%" + stringObjectEntry.getValue() + "%")));
  }
}
