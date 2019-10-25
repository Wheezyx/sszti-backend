package pl.wedel.szzti.repository;

import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wedel.szzti.domain.Rental;

public interface RentalRepository extends PagingAndSortingRepository<Rental, UUID> {

}
