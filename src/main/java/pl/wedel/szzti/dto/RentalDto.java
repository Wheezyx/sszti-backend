package pl.wedel.szzti.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode(of = "item")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class RentalDto {

  private ItemDto item;

  private PlaceDto place;

  private RenterDto renter;

  private LocalDate start;

  private LocalDate end;
}
