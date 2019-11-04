package pl.wedel.szzti.validation;

import org.springframework.stereotype.Service;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.dto.PlaceDto;
import pl.wedel.szzti.exception.ValidationException;

@Service
public class PlaceDtoValidator {

  public void validate(PlaceDto placeDto) {
    if (null == placeDto.getName()) {
      throw new ValidationException(new ErrorMessage("Place name cannot be null"));
    }
  }
}
