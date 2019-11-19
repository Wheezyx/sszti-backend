package pl.wedel.szzti.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.dto.ItemDto;
import pl.wedel.szzti.dto.PlaceDto;
import pl.wedel.szzti.dto.RentalDto;
import pl.wedel.szzti.dto.RentalWithManyItemsDto;
import pl.wedel.szzti.dto.RenterDto;
import pl.wedel.szzti.exception.ValidationException;
import pl.wedel.szzti.repository.RentalRepository;

@Service
@AllArgsConstructor
public class RentalDtoValidator {

  private final RentalRepository rentalRepository;

  public void validateRental(RentalDto rentalDto) {
    validatePlaceAndRenter(rentalDto.getPlace(), rentalDto.getRenter());
    validateItem(rentalDto.getItem());
  }

  public void validateRentalWithManyItems(RentalWithManyItemsDto rentalDto) {
    validatePlaceAndRenter(rentalDto.getPlace(), rentalDto.getRenter());
    rentalDto.getItems().forEach(this::validateItem);
  }

  private void validatePlaceAndRenter(PlaceDto placeDto, RenterDto renterDto) {
    if (placeDto == null && renterDto == null) {
      throw new ValidationException(
          new ErrorMessage("Renter id and place id are null. Provide at least one."));
    }
  }

  private void validateItem(ItemDto itemDto) {
    if (itemDto == null || itemDto.getId() == null) {
      throw new ValidationException(new ErrorMessage("Item id cannot be null"));
    }
  }


}
