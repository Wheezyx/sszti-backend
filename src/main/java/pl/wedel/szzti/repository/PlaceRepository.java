package pl.wedel.szzti.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wedel.szzti.domain.Place;

public interface PlaceRepository extends PagingAndSortingRepository<Place, UUID> {

  Page<Place> findAllByNameContaining(String name, Pageable pageable);
}
