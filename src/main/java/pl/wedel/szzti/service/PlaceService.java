package pl.wedel.szzti.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Place;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.NotFoundException;
import pl.wedel.szzti.repository.PlaceRepository;

@Service
@AllArgsConstructor
@Slf4j
public class PlaceService {

  private final PlaceRepository placeRepository;

  public Page<Place> findAll(Pageable pageable) {
    log.debug("Finding all places");
    return placeRepository.findAll(pageable);
  }

  public Place save(Place place) {
    log.debug("Saving place with name: {}", place.getName());
    return placeRepository.save(place);
  }

  public Place findById(UUID id) {
    log.debug("Finding place with id {}", id);
    return placeRepository.findById(id).orElseThrow(() -> new NotFoundException(
        new ErrorMessage(String.format("Place with id %s not found.", id.toString()))));
  }
}
