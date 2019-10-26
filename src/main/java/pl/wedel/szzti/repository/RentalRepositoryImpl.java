package pl.wedel.szzti.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.wedel.szzti.domain.Rental;
import pl.wedel.szzti.web.RentalSearchParameters;

public class RentalRepositoryImpl implements SearchRentalRepository {

  private static final String ID = "id";
  private static final String ITEM_ID = "itemId";
  private static final String PLACE_ID = "placeId";
  private static final String RENTER_ID = "renterId";


  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Page<Rental> search(RentalSearchParameters searchParameters, Pageable pageable) {
    CriteriaBuilder qb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Rental> cq = qb.createQuery(Rental.class);
    List<Predicate> wheres = new ArrayList<>();

    if (searchParameters.containsKey(ID)) {
      wheres.add(setQueryParameter())
    }

    Root<Rental> root = cq.from(Rental.class);
    cq.where(qb.and(
        qb.equal(root.get(ID), qb.parameter(UUID.class, ID)),
        qb.equal(root.get(ITEM_ID), qb.parameter(Integer.class, ITEM_ID)),
        qb.equal(root.get(PLACE_ID), qb.parameter(Integer.class, PLACE_ID)),
        qb.equal(root.get(RENTER_ID), qb.parameter(Integer.class, RENTER_ID))
    ));
    Query query = entityManager.createQuery(cq);

    //ADD IFS/ PREDICATES BASED

  }

  private Predicate setQueryParameter(CriteriaBuilder qb, Root root,
      RentalSearchParameters searchParameters, String param) {
    if (searchParameters.containsKey(param)){
    return qb.equal(root.get(param), qb.parameter(UUID.class, param);
  }
}
