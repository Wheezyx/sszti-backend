package pl.wedel.szzti.web;

import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
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
import pl.wedel.szzti.dto.RenterDto;
import pl.wedel.szzti.mapper.RenterMapper;
import pl.wedel.szzti.service.RenterService;
import pl.wedel.szzti.validation.RenterDtoValidator;

@RestController
@RequestMapping("/api/renters")
@AllArgsConstructor
public class RenterController {

  private final RenterMapper renterMapper;
  private final RenterService renterService;
  private final RenterDtoValidator renterDtoValidator;

  @GetMapping
  public Page<RenterDto> searchRenters(Pageable pageable,
      @RequestParam Map<String, String> params) {
    return renterService.search(pageable, params).map(renterMapper::toDto);
  }

  @PostMapping
  public RenterDto saveRenter(@RequestBody RenterDto renterDto) {
    renterDtoValidator.validate(renterDto);
    return renterMapper.toDto(renterService.save(renterMapper.fromDto(renterDto)));
  }


  @GetMapping("/{id}")
  public RenterDto getRenter(@PathVariable("id") UUID id) {
    return renterMapper.toDto(renterService.findById(id));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePlace(@PathVariable("id") UUID id) {
    this.renterService.deleteById(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RenterDto updateRenter(@PathVariable("id") UUID id, @RequestBody RenterDto renterDto) {
    renterDtoValidator.validate(id, renterDto);

    return renterMapper.toDto(renterService.update(renterMapper.fromDto(renterDto)));
  }

}
