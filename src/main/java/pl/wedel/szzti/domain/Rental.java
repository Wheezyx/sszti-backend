package pl.wedel.szzti.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "RENTALS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rental extends BaseEntity {

  @OneToOne(fetch = FetchType.EAGER)
  @MapsId
  private Item item;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "place_id")
  private Place place;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "renter_id")
  private Renter renter;

  private LocalDate startDate;

  private LocalDate endDate;
}
