package pl.wedel.szzti.validation;

import java.util.UUID;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.dto.PlaceDto;
import pl.wedel.szzti.exception.ValidationException;

@Service
public class PlaceDtoValidator {

  public void validate(UUID id, PlaceDto placeDto) {
    if (placeDto.getId() == null || !id.equals(placeDto.getId())) {
      throw new ValidationException(new ErrorMessage("Renter id must match."));
    }
    validate(placeDto);
  }


  public void validate(PlaceDto placeDto) {
    if (null == placeDto.getName()) {
      throw new ValidationException(new ErrorMessage("Place name cannot be null"));
    }
  }
}
