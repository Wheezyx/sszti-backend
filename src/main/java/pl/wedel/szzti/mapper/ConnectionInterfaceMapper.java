package pl.wedel.szzti.mapper;

import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.ConnectionInterface;
import pl.wedel.szzti.domain.Host;
import pl.wedel.szzti.dto.ConnectionInterfaceDto;

@Service
public class ConnectionInterfaceMapper {

  public ConnectionInterfaceDto toDto(ConnectionInterface connectionInterface) {
    if (connectionInterface == null) {
      return null;
    }
    ConnectionInterfaceDto interfaceDto = ConnectionInterfaceDto.builder()
        .connectionName(connectionInterface.getConnectionName())
        .connectionNumber(connectionInterface.getConnectionNumber())
        .MAC(connectionInterface.getMac())
        .ip(connectionInterface.getIp())
        .socketNumber(connectionInterface.getSocketNumber())
        .vlan(connectionInterface.getVlan())
        .patchPanel(connectionInterface.getPatchPanel())
        .build();

    interfaceDto.setId(connectionInterface.getId());
    return interfaceDto;
  }

  public ConnectionInterface fromDto(ConnectionInterfaceDto connectionInterfaceDto) {

    if (connectionInterfaceDto == null) {
      return null;
    }

    ConnectionInterface connectionInterface = ConnectionInterface.builder()
        .connectionName(connectionInterfaceDto.getConnectionName())
        .connectionNumber(connectionInterfaceDto.getConnectionNumber())
        .mac(connectionInterfaceDto.getMAC())
        .ip(connectionInterfaceDto.getIp())
        .socketNumber(connectionInterfaceDto.getSocketNumber())
        .vlan(connectionInterfaceDto.getVlan())
        .patchPanel(connectionInterfaceDto.getPatchPanel())
        .build();
    connectionInterface.setId(connectionInterfaceDto.getId());

    if (connectionInterfaceDto.getHostId() != null) {
      Host host = new Host();
      host.setId(connectionInterfaceDto.getHostId());
      connectionInterface.setHost(host);
    }
    return connectionInterface;
  }
}
