package pl.wedel.szzti.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessLoginDto {
  private String username;
  private String token;
  private Set<String> roles;
}
