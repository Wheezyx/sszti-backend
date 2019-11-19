package pl.wedel.szzti.web;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wedel.szzti.domain.Rental;
import pl.wedel.szzti.dto.RentalDto;
import pl.wedel.szzti.dto.RentalWithManyItemsDto;
import pl.wedel.szzti.mapper.RentalMapper;
import pl.wedel.szzti.service.RentalService;
import pl.wedel.szzti.validation.RentalDtoValidator;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
@Slf4j
public class RentalController {

  private final RentalService rentalService;
  private final RentalMapper rentalMapper;
  private final RentalDtoValidator rentalDtoValidator;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<RentalDto> searchRentals(@RequestParam Map<String, Object> params,
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
  public Iterable<Rental> saveRental(@RequestBody RentalWithManyItemsDto rentalDto) {
    rentalDtoValidator.validateRentalWithManyItems(rentalDto);
    List<Rental> rentals = rentalMapper.mapToListOfRentals(rentalDto);
    //TODO CONVERT TO DTO
    return rentalService.saveAll(rentals);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void saveRental(@PathVariable("id") UUID id) {
    rentalService.remove(id);
  }

}
