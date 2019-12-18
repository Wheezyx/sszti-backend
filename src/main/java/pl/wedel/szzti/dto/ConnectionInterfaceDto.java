package pl.wedel.szzti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@Setter
public class ConnectionInterfaceDto extends BaseDto {

  private String ip;
  private String MAC;
  private String socketNumber;
  private String vlan;
  private String patchPanel;
  private String connectionName;
  private String connectionNumber;

  @JsonProperty(access = Access.WRITE_ONLY)
  private UUID hostId;

}
