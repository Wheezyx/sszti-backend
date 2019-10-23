package pl.wedel.szzti.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.wedel.szzti.dto.ErrorMessage;

@ControllerAdvice
@Slf4j
public class WebErrorHandling {

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
}
