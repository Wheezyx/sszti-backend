package pl.wedel.szzti.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
public class RenterDto extends BaseDto {

  private String name;
  private String surname;
  private String code;

  private Set<ItemDto> itemRentals;
}
