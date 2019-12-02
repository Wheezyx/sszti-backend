package pl.wedel.szzti.validation;

import java.util.UUID;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.dto.RenterDto;
import pl.wedel.szzti.exception.ValidationException;

@Service
public class RenterDtoValidator {


  public void validate(UUID id, RenterDto renterDto) {
    if (renterDto.getId() == null || !id.equals(renterDto.getId())) {
      throw new ValidationException(new ErrorMessage("Renter id must match."));
    }
    validate(renterDto);
  }


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
