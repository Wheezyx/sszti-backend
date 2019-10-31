package pl.wedel.szzti.mapper;

import static org.junit.Assert.assertEquals;

import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import pl.wedel.szzti.domain.Renter;
import pl.wedel.szzti.dto.RenterDto;

public class RenterMapperTest {

  private RenterMapper renterMapper;

  @Before
  public void setUp() throws Exception {
    renterMapper = new RenterMapper();
  }

  @Test
  public void shouldConvertToDto() {
    String code = "test";
    String name = "test1";
    String surname = "test2";
    UUID id = UUID.randomUUID();
    Renter renter = new Renter();
    renter.setCode(code);
    renter.setName(name);
    renter.setSurname(surname);
    renter.setId(id);

    RenterDto renterDto = renterMapper.toDto(renter);

    assertEquals(renterDto.getId(), renter.getId());
  }
}
