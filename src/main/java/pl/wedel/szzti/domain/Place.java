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
@NoArgsConstructor
@Table(name = "places", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"}, name = "place_name_idx")})
public class Place extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "place",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<Rental> itemRentals;
}
