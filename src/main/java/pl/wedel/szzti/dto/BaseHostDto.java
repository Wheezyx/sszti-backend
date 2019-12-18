package pl.wedel.szzti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@Setter
public class BaseHostDto extends BaseDto {

  private String name;
  private String place;
  private String inventoryCode;

}
