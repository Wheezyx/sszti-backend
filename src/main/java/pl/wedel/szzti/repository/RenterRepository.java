package pl.wedel.szzti.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wedel.szzti.domain.Renter;

public interface RenterRepository extends PagingAndSortingRepository<Renter, UUID> {

  Page<Renter> findByCodeContaining(Pageable pageable, String code);

}
