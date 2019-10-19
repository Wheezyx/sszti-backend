package pl.wedel.szzti.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "place")
public class Place extends BaseEntity {

  private String name;

  @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
  private Set<PlaceItem> placeItems;
}
