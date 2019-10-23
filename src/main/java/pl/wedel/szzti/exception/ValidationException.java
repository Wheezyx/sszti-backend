package pl.wedel.szzti.exception;

import lombok.Getter;
import pl.wedel.szzti.dto.ErrorMessage;

public class ValidationException extends RuntimeException {

  @Getter
  private final ErrorMessage errorMessage;

  public ValidationException(ErrorMessage message) {
    super(message.toString());
    this.errorMessage = message;
  }
}
