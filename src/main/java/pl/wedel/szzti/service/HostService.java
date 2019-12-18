package pl.wedel.szzti.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Host;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.NotFoundException;
import pl.wedel.szzti.repository.HostRepository;
import pl.wedel.szzti.web.HostSearchParameters;

@Service
@Slf4j
@AllArgsConstructor
public class HostService {

  private final HostRepository hostRepository;

  public Page<Host> search(HostSearchParameters searchParameters, Pageable pageable) {
    log.debug("Finding hosts.");
    return hostRepository.search(searchParameters, pageable);
  }

  public Host findById(UUID id) {
    log.debug("Finding host with id {}", id);
    return hostRepository.findById(id).orElseThrow(() -> new NotFoundException(
        new ErrorMessage(String.format("Host with id %s not found.", id.toString()))));
  }

  public Host save(Host host) {
    log.debug("Saving host");
    return this.hostRepository.save(host);
  }

  public Host update(Host host) {
    log.debug("Updating host with id: {}", host.getId());
    return hostRepository.save(host);
  }

  public void deleteById(UUID hostId) {
    log.debug("Removing item with id: {}", hostId);
    this.hostRepository.deleteById(hostId);
  }
}
