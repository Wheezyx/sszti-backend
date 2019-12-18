package pl.wedel.szzti.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "hosts")
@NoArgsConstructor
@Builder
public class Host extends BaseEntity {

  @OneToMany(
      mappedBy = "host",
      cascade = CascadeType.ALL
  )
  Set<ConnectionInterface> connectionInterfaces;
  private String name;
  //TODO Use Place entity instead of raw string
  private String place;
  private String inventoryCode;
}
