package pl.wedel.szzti.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.ConnectionInterface;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.NotFoundException;
import pl.wedel.szzti.repository.ConnectionInterfaceRepository;

@Service
@AllArgsConstructor
@Slf4j
public class ConnectionInterfaceService {

  private final ConnectionInterfaceRepository connectionInterfaceRepository;

  public ConnectionInterface save(ConnectionInterface connectionInterface) {
    log.debug("Saving connectionInterface");
    return connectionInterfaceRepository.save(connectionInterface);
  }

  public ConnectionInterface update(ConnectionInterface connectionInterface) {
    log.debug("Updating connectionInterface with id: {}", connectionInterface.getId());
    return connectionInterfaceRepository.save(connectionInterface);
  }

  public void deleteById(UUID connectionInterfaceId) {
    log.debug("Removing connectionInterface with id: {}", connectionInterfaceId);
    this.connectionInterfaceRepository.deleteById(connectionInterfaceId);
  }

  public ConnectionInterface getById(UUID connectionInterfaceId) {
    log.debug("Finding connectionInterface with id {}", connectionInterfaceId);
    return connectionInterfaceRepository.findById(connectionInterfaceId)
        .orElseThrow(() -> new NotFoundException(
            new ErrorMessage(String.format("Connection interface with id %s not found.",
                connectionInterfaceId.toString()))));
  }
}
