package pl.wedel.szzti.mapper;

import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Host;
import pl.wedel.szzti.dto.BaseHostDto;
import pl.wedel.szzti.dto.HostDto;

@Service
@AllArgsConstructor
public class HostMapper {

  private final ConnectionInterfaceMapper connectionInterfaceMapper;

  public BaseHostDto toBaseDto(Host host) {
    if (host == null) {
      return null;
    }

    BaseHostDto baseHostDto = BaseHostDto.builder()
        .name(host.getName())
        .place(host.getPlace())
        .inventoryCode(host.getInventoryCode())
        .build();
    baseHostDto.setId(host.getId());
    return baseHostDto;
  }


  public HostDto toDto(Host host) {
    if (host == null) {
      return null;
    }

    HostDto hostDto = new HostDto();
    hostDto.setName(host.getName());
    hostDto.setPlace(host.getPlace());
    hostDto.setInventoryCode(host.getInventoryCode());
    if (host.getConnectionInterfaces() != null) {
      hostDto.setConnectionInterfaces(
          host.getConnectionInterfaces().stream()
              .map(connectionInterfaceMapper::toDto)
              .collect(Collectors.toSet()));
    }
    hostDto.setId(host.getId());
    return hostDto;
  }

  public Host fromDto(BaseHostDto baseHostDto) {
    return Host.builder()
        .name(baseHostDto.getName())
        .inventoryCode(baseHostDto.getInventoryCode())
        .place(baseHostDto.getPlace())
        .build();
  }
}
