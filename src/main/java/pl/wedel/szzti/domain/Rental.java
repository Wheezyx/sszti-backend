package pl.wedel.szzti.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "RENTALS")
@AllArgsConstructor
@NoArgsConstructor
public class Rental extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "place_id")
  private Place place;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "renter_id")
  private Renter renter;

  private LocalDate start;

  private LocalDate end;
}
