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
import pl.wedel.szzti.domain.Host;
import pl.wedel.szzti.dto.BaseHostDto;
import pl.wedel.szzti.mapper.HostMapper;
import pl.wedel.szzti.service.HostService;

@RestController
@RequestMapping("/api/hosts")
@AllArgsConstructor
public class HostController {

  private final HostMapper hostMapper;
  private final HostService hostService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  //TODO Add search parameters
  public Page<BaseHostDto> search(@RequestParam Map<String, Object> filters, Pageable pageable) {
    HostSearchParameters searchParameters = new HostSearchParameters(filters);
    return hostService.search(searchParameters, pageable).map(hostMapper::toBaseDto);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BaseHostDto saveHost(@RequestBody BaseHostDto baseHostDto) {
    //TODO ADD VALIDATION
    Host host = hostMapper.fromDto(baseHostDto);
    return hostMapper.toDto(hostService.save(host));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BaseHostDto updateHost(@PathVariable("id") UUID hostId,
      @RequestBody BaseHostDto baseHostDto) {
    //TODO ADD VALIDATION
    Host host = hostMapper.fromDto(baseHostDto);
    return hostMapper.toDto(hostService.update(host));
  }

  @GetMapping(value = "/{id}")
  public BaseHostDto getHost(@PathVariable("id") UUID hostId) {
    return hostMapper.toDto(hostService.findById(hostId));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteHost(@PathVariable("id") UUID hostId) {
    hostService.deleteById(hostId);
  }

}
