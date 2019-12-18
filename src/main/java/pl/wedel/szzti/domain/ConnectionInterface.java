package pl.wedel.szzti.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode(callSuper = true, exclude = "host")
@ToString(callSuper = true, exclude = "host")
@Table(name = "connection_interfaces")
@NoArgsConstructor
@Builder
public class ConnectionInterface extends BaseEntity {

  private String ip;
  private String mac;
  private String socketNumber;
  private String vlan;
  private String patchPanel;
  private String connectionName;
  private String connectionNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "host_id")
  private Host host;
}
