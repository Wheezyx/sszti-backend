package pl.wedel.szzti.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "renters", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code"}, name = "renter_code_idx")})
@NoArgsConstructor
public class Renter extends BaseEntity {

  private String firstName;
  private String lastName;
  @Column(nullable = false)
  private String code;

  @OneToMany(mappedBy = "renter",
      cascade = CascadeType.ALL)
  private Set<Rental> itemRentals;
}
