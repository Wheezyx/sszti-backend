package pl.wedel.szzti.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Renter;
import pl.wedel.szzti.dto.RenterDto;

@Service
@AllArgsConstructor
public class RenterMapper {

  public RenterDto toDto(Renter renter) {
    if (null == renter) {
      return null;
    }

    RenterDto renterDto = RenterDto.builder()
        .code(renter.getCode())
        .name(renter.getFirstName())
        .surname(renter.getLastName())
        .build();
    renterDto.setId(renter.getId());
    return renterDto;
  }

  public Renter fromDto(RenterDto renterDto) {
    if (renterDto == null) {
      return null;
    }
    Renter renter = new Renter();
    renter.setCode(renterDto.getCode());
    renter.setFirstName(renterDto.getName());
    renter.setLastName(renterDto.getSurname());
    renter.setId(renterDto.getId());

    return renter;
  }
}
