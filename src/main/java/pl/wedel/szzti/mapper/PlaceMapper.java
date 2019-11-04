package pl.wedel.szzti.mapper;

import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Place;
import pl.wedel.szzti.dto.PlaceDto;

@Service
public class PlaceMapper {

  public PlaceDto toDto(Place place) {
    if (null == place) {
      return null;
    }

    PlaceDto placeDto = PlaceDto.builder()
        .name(place.getName())
        .build();
    placeDto.setId(place.getId());
    return placeDto;
  }

  public Place fromDto(PlaceDto placeDto) {
    Place place = new Place();
    place.setName(placeDto.getName());
    place.setId(placeDto.getId());
    return place;
  }
}
