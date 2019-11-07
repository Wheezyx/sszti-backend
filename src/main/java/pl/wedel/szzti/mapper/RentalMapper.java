package pl.wedel.szzti.mapper;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.domain.Rental;
import pl.wedel.szzti.domain.Renter;
import pl.wedel.szzti.dto.RentalDto;
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

  //TODO Consider remove fetching items, renters and places and just paste id
  // to let db handle possible not existing keys.

  public Rental fromDto(RentalDto rentalDto) {
    Rental rental = new Rental();
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
}
