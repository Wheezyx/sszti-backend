package pl.wedel.szzti.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"item", "place", "renter"})
@Table(name = "rentals", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"item_id"}, name = "rental_item_pkey")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rental extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "place_id")
  private Place place;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "renter_id")
  private Renter renter;

  private LocalDate startDate;

  private LocalDate endDate;
}
