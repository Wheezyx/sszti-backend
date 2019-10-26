package pl.wedel.szzti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.wedel.szzti.domain.Rental;
import pl.wedel.szzti.web.RentalSearchParameters;

public interface SearchRentalRepository {

  Page<Rental> search(RentalSearchParameters searchParameters, Pageable pageable);
}
