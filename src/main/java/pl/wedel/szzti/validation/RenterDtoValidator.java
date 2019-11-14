package pl.wedel.szzti.validation;

import org.springframework.stereotype.Service;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.dto.RenterDto;
import pl.wedel.szzti.exception.ValidationException;

@Service
public class RenterDtoValidator {

  public void validate(RenterDto renterDto) {
    if (renterDto.getCode() == null) {
      throw new ValidationException(new ErrorMessage("Renter code cannot be null"));

    }
    if (renterDto.getName() == null) {
      throw new ValidationException(new ErrorMessage("Renter name cannot be null"));

    }
    if (renterDto.getSurname() == null) {
      throw new ValidationException(new ErrorMessage("Renter surname cannot be null"));
    }
  }
}
