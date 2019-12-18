package pl.wedel.szzti.web;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wedel.szzti.domain.ConnectionInterface;
import pl.wedel.szzti.dto.ConnectionInterfaceDto;
import pl.wedel.szzti.mapper.ConnectionInterfaceMapper;
import pl.wedel.szzti.service.ConnectionInterfaceService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/connectionInterfaces")
public class ConnectionInterfaceController {

  private final ConnectionInterfaceService connectionInterfaceService;

  private final ConnectionInterfaceMapper connectionInterfaceMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ConnectionInterfaceDto saveConnectionInterface(
      @RequestBody ConnectionInterfaceDto connectionInterfaceDto) {
    //TODO ADD CIDTO VALIDATOR
    ConnectionInterface ci = connectionInterfaceMapper.fromDto(connectionInterfaceDto);
    return connectionInterfaceMapper.toDto(connectionInterfaceService.save(ci));
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ConnectionInterfaceDto getConnectionInterface(@PathVariable("id") UUID ciId) {
    return connectionInterfaceMapper.toDto(connectionInterfaceService.getById(ciId));
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ConnectionInterfaceDto updateItem(@PathVariable("id") UUID ciId,
      @RequestBody ConnectionInterfaceDto connectionInterfaceDto) {
    //TODO ADD CIDTO VALIDATOR
    ConnectionInterface ci = connectionInterfaceMapper.fromDto(connectionInterfaceDto);
    return connectionInterfaceMapper.toDto(connectionInterfaceService.update(ci));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeItem(@PathVariable("id") UUID ciId) {
    connectionInterfaceService.deleteById(ciId);
  }

}
