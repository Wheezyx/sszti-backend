package pl.wedel.szzti.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode(of = "items")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class RentalWithManyItemsDto {
  private List<ItemDto> items;

  private PlaceDto place;

  private RenterDto renter;
}
