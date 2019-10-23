package pl.wedel.szzti.exception;

import lombok.Getter;
import pl.wedel.szzti.dto.ErrorMessage;

public class NotFoundException extends RuntimeException {

  @Getter
  private final ErrorMessage errorMessage;

  public NotFoundException(ErrorMessage message) {
    super(message.toString());
    this.errorMessage = message;
  }

}
