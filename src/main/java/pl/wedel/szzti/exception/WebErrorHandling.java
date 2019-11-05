package pl.wedel.szzti.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.wedel.szzti.dto.ErrorMessage;

@ControllerAdvice
@Slf4j
public class WebErrorHandling {

  private static final Map<String, String> UNIQUE_CONSTRAINT_MAP = new HashMap<>();

  static {
    UNIQUE_CONSTRAINT_MAP.put("place_name_idx", "Place with provided name already exists");
    UNIQUE_CONSTRAINT_MAP.put("renter_code_idx", "Renter with provided code already exists");
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorMessage handleNotFoundException(NotFoundException ex) {
    log.info(ex.getMessage());
    return ex.getErrorMessage();
  }

  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorMessage handleValidationException(ValidationException ex) {
    log.info(ex.getMessage());
    return ex.getErrorMessage();
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorMessage handleDataIntegrityException(DataIntegrityViolationException ex) {
    log.info(ex.getMessage());

    if (ex.getCause() instanceof ConstraintViolationException) {
      ConstraintViolationException cause = (ConstraintViolationException) ex.getCause();
      String message = UNIQUE_CONSTRAINT_MAP.getOrDefault(cause.getConstraintName(), null);

      if (message == null) {
        return new ErrorMessage(
            "No message found for constraint: " + cause.getConstraintName());
      }
      return new ErrorMessage(message);
    }
    return new ErrorMessage(ex.getMessage());
  }
}
