package pl.wedel.szzti.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Renter;
import pl.wedel.szzti.dto.RenterDto;

@Service
@AllArgsConstructor
public class RenterMapper {

  public RenterDto toDto(Renter renter) {
    RenterDto renterDto = RenterDto.builder()
        .code(renter.getCode())
        .name(renter.getName())
        .surname(renter.getSurname())
        .build();
    renterDto.setId(renter.getId());
    return renterDto;
  }
}
