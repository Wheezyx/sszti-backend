package pl.wedel.szzti.web;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wedel.szzti.dto.RentalDto;
import pl.wedel.szzti.mapper.RentalMapper;
import pl.wedel.szzti.service.RentalService;
import pl.wedel.szzti.validation.RentalValidator;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
@Slf4j
public class RentalController {

  private final RentalService rentalService;
  private final RentalMapper rentalMapper;
  private final RentalValidator rentalValidator;

  //TODO ADD SEARCH BY ITEM ID, RENTER ID AND PLACE ID, MAP!!!!!

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<RentalDto> searchRentals(@RequestParam HashMap<String, Object> params,
      Pageable pageable) {
    log.debug(params.toString());

    RentalSearchParameters searchParameters = new RentalSearchParameters(params);
    return rentalService.search(searchParameters, pageable).map(rentalMapper::toDto);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RentalDto getRetal(@PathVariable("id") UUID id) {
    return rentalMapper.toDto(rentalService.findById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RentalDto saveRental(@RequestBody RentalDto rentalDto) {
    rentalValidator.validateRental(rentalDto);
    return rentalMapper.toDto(rentalService.save(rentalMapper.fromDto(rentalDto)));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void saveRental(@PathVariable("id") UUID id) {
    rentalService.remove(id);
  }

}
