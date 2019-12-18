package pl.wedel.szzti.validation;

import java.util.UUID;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.dto.ItemDto;
import pl.wedel.szzti.exception.ValidationException;

@Service
public class ItemDtoValidator {

  public void validate(UUID itemId, ItemDto itemDto) {
    if (!itemId.equals(itemDto.getId())) {
      throw new ValidationException(new ErrorMessage("Item id mismatch."));
    }
    validate(itemDto);
  }

  public void validate(ItemDto itemDto) {
    return;
  }

}
