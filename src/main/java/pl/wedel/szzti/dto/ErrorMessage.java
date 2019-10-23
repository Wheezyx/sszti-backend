package pl.wedel.szzti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class ErrorMessage {

  @JsonProperty(access = Access.READ_ONLY)
  private String message;
}
