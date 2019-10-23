package pl.wedel.szzti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public final class ErrorMessage {

  @JsonProperty(access = Access.READ_ONLY)
  private String message;
}
