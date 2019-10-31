package pl.wedel.szzti.validation;

import java.util.Arrays;
import java.util.UUID;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.InsideType;
import pl.wedel.szzti.domain.ItemType;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.dto.ItemDto;
import pl.wedel.szzti.exception.ValidationException;

@Service
public class ItemDtoValidator {

  public void validateItem(ItemDto itemDto) {
    validate(itemDto);
  }

  public void validateItem(UUID itemId, ItemDto itemDto) {
    if (!itemId.equals(itemDto.getId())) {
      throw new ValidationException(new ErrorMessage("Item id mismatch."));
    }
    validate(itemDto);
  }

  private void validate(ItemDto itemDto) {
    if (null == InsideType.fromString(itemDto.getInsideType())) {
      throw new ValidationException(new ErrorMessage("Invalid inside type. Available: " + Arrays
          .toString(InsideType.values())));
    }

    if (null == ItemType.fromString(itemDto.getItemType())) {
      throw new ValidationException(new ErrorMessage("Invalid item type. Available: " + Arrays
          .toString(ItemType.values())));
    }
  }

}
