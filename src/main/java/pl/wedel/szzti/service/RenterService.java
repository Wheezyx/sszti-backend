package pl.wedel.szzti.service;

import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Renter;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.NotFoundException;
import pl.wedel.szzti.repository.RenterRepository;

@Service
@AllArgsConstructor
@Slf4j
public class RenterService {

  private static final String CODE = "code";

  private final RenterRepository renterRepository;

  public Page<Renter> search(Pageable pageable, Map<String, String> params) {

    if (params.containsKey(CODE)) {
      log.debug("Finding renters with code {}", params.get(CODE));
      return renterRepository.findByCode(pageable, params.get(CODE));
    }

    log.debug("Finding all renters.");
    return renterRepository.findAll(pageable);
  }

  public Renter findById(UUID id) {
    log.debug("Finding item with renter {}", id);
    return renterRepository.findById(id).orElseThrow(() -> new NotFoundException(
        new ErrorMessage(String.format("Renter with id %s not found.", id.toString()))));
  }

  public Renter save(Renter renter) {
    log.debug("Saving renter");
    return renterRepository.save(renter);
  }

  public Renter update(Renter renter) {
    return renterRepository.save(renter);
  }

}