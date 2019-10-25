package pl.wedel.szzti.web;

import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wedel.szzti.dto.RenterDto;
import pl.wedel.szzti.mapper.RenterMapper;
import pl.wedel.szzti.service.RenterService;

@RestController
@RequestMapping("/api/renters")
@AllArgsConstructor
public class RenterController {

  private final RenterMapper renterMapper;
  private final RenterService renterService;

  @GetMapping
  public Page<RenterDto> searchRenters(Pageable pageable,
      @RequestParam Map<String, String> params) {
    return renterService.search(pageable, params).map(renterMapper::toDto);
  }

  @GetMapping("/{id}")
  public RenterDto getRenter(@PathVariable("id") UUID id) {
    return renterMapper.toDto(renterService.findById(id));
  }


}
