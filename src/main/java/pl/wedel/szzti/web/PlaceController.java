package pl.wedel.szzti.web;

import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wedel.szzti.domain.Place;
import pl.wedel.szzti.dto.PlaceDto;
import pl.wedel.szzti.mapper.PlaceMapper;
import pl.wedel.szzti.service.PlaceService;
import pl.wedel.szzti.validation.PlaceDtoValidator;

@RestController
@RequestMapping("/api/places")
@Slf4j
@AllArgsConstructor
public class PlaceController {

  private final PlaceService placeService;
  private final PlaceMapper placeMapper;
  private final PlaceDtoValidator placeDtoValidator;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<PlaceDto> searchPlaces(@RequestParam Map<String, Object> params, Pageable pageable) {

    PlaceSearchParameters searchParameters = new PlaceSearchParameters(params);

    return placeService.search(searchParameters, pageable).map(placeMapper::toDto);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PlaceDto savePlace(@RequestBody PlaceDto placeDto) {
    placeDtoValidator.validate(placeDto);
    Place place = placeMapper.fromDto(placeDto);
    return placeMapper.toDto(placeService.save(place));
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PlaceDto getPlace(@PathVariable("id") UUID placeId) {
    Place place = placeService.findById(placeId);
    return placeMapper.toDto(place);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePlace(@PathVariable("id") UUID placeId) {
    placeService.removeById(placeId);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PlaceDto updatePlace(@PathVariable("id") UUID id, @RequestBody PlaceDto placeDto) {
    placeDtoValidator.validate(id, placeDto);

    return placeMapper.toDto(placeService.update(placeMapper.fromDto(placeDto)));
  }
}
