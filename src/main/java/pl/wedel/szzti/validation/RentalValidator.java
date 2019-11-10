package pl.wedel.szzti.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.dto.RentalDto;
import pl.wedel.szzti.exception.ValidationException;
import pl.wedel.szzti.repository.RentalRepository;

@Service
@AllArgsConstructor
public class RentalValidator {

  private final RentalRepository rentalRepository;

  public void validateRental(RentalDto rentalDto) {
    if (rentalDto.getPlace() == null && rentalDto.getRenter() == null) {
      throw new ValidationException(
          new ErrorMessage("Renter id and place id are null. Provide at least one."));
    }

    if (rentalDto.getItem() == null || rentalDto.getItem().getId() == null) {
      throw new ValidationException(new ErrorMessage("Item id cannot be null"));
    }

  }
}
