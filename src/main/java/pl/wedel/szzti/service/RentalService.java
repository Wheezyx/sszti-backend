package pl.wedel.szzti.service;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Rental;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.NotFoundException;
import pl.wedel.szzti.repository.RentalRepository;
import pl.wedel.szzti.web.RentalSearchParameters;

@Service
@AllArgsConstructor
public class RentalService {

  private final RentalRepository rentalRepository;

  public Page<Rental> search(RentalSearchParameters searchParameters, Pageable pageable) {
    return rentalRepository.search(searchParameters, pageable);
  }

  public Rental save(Rental rental) {
    return rentalRepository.save(rental);
  }

  public Iterable<Rental> saveAll(List<Rental> rentals) {
    return rentalRepository.saveAll(rentals);
  }

  public void remove(UUID id) {
    rentalRepository.deleteById(id);
  }

  public Rental findById(UUID id) {
    return rentalRepository.findById(id).orElseThrow(() -> new NotFoundException(
        new ErrorMessage(String.format("Item with id %s not found.", id.toString()))));
  }
}
