package pl.wedel.szzti.utils;

import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.ValidationException;

@UtilityClass
public class UUIDHelper {

  public static void validateUuidFormat(Object o) {
    Pattern pattern = Pattern
        .compile("/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/");
    if (pattern.matcher((CharSequence) o).matches()) {
      throw new ValidationException(new ErrorMessage("Invalid UUID format"));
    }
  }
}
