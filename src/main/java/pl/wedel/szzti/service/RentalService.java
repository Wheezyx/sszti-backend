package pl.wedel.szzti.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Rental;
import pl.wedel.szzti.repository.RentalRepository;
import pl.wedel.szzti.web.RentalSearchParameters;

@Service
@AllArgsConstructor
public class RentalService {

  private final RentalRepository rentalRepository;

  public Page<Rental> search(RentalSearchParameters searchParameters, Pageable pageable) {
  }


  public Rental save(Rental rental) {
    return rentalRepository.save(rental);
  }

}
