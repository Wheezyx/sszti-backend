package pl.wedel.szzti.repository;

import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wedel.szzti.domain.Place;

public interface PlaceRepository extends PagingAndSortingRepository<Place, UUID> {

}
