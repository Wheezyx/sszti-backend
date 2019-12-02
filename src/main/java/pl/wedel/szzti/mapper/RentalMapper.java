package pl.wedel.szzti.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.domain.Place;
import pl.wedel.szzti.domain.Rental;
import pl.wedel.szzti.domain.Renter;
import pl.wedel.szzti.dto.ItemDto;
import pl.wedel.szzti.dto.RentalDto;
import pl.wedel.szzti.dto.RentalWithManyItemsDto;
import pl.wedel.szzti.service.ItemService;
import pl.wedel.szzti.service.RenterService;

@Service
@AllArgsConstructor
public class RentalMapper {

  private final ItemMapper itemMapper;
  private final RenterMapper renterMapper;
  private final PlaceMapper placeMapper;
  private final ItemService itemService;
  private final RenterService renterService;

  public Rental fromDto(RentalDto rentalDto) {
    Rental rental = new Rental();
    //TODO REPLACE WITH MAPPER CALLS AND LET DB HANDLE (?)
    // THE EXCEPTION WITH NON EXISTING FOREIGN KEY
    final Item item = itemService.findById(rentalDto.getItem().getId());
    final Renter renter = renterService.findById(rentalDto.getRenter().getId());

    rental.setStartDate(rentalDto.getStart() == null ? LocalDate.now() : rentalDto.getStart());
    rental.setEndDate(rentalDto.getEnd());
    rental.setItem(item);
    rental.setRenter(renter);
    return rental;
  }

  public RentalDto toDto(Rental rental) {
    if (null == rental) {
      return null;
    }
    RentalDto rentalDto = new RentalDto();
    rentalDto.setStart(rental.getStartDate());
    rentalDto.setEnd(rental.getEndDate());
    rentalDto.setItem(itemMapper.toDto(rental.getItem()));
    rentalDto.setRenter(renterMapper.toDto(rental.getRenter()));
    rentalDto.setPlace(placeMapper.toDto(rental.getPlace()));
    rentalDto.setId(rental.getId());
    return rentalDto;

  }

  public List<Rental> mapToListOfRentals(RentalWithManyItemsDto rentalDto) {
    return rentalDto.getItems().stream()
        .map(item -> mapToRental(item, rentalDto)).collect(Collectors.toList());
  }

  private Rental mapToRental(ItemDto itemDto, RentalWithManyItemsDto rentalDto) {
    Rental rental = new Rental();
    Item item = itemMapper.fromDto(itemDto);
    Renter renter = renterMapper.fromDto(rentalDto.getRenter());
    Place place = placeMapper.fromDto(rentalDto.getPlace());
    rental.setItem(item);
    rental.setRenter(renter);
    rental.setPlace(place);
    return rental;
  }
}
