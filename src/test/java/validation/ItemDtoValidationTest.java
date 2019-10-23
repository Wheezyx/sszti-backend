package validation;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.wedel.szzti.domain.InsideType;
import pl.wedel.szzti.domain.ItemType;
import pl.wedel.szzti.dto.ItemDto;
import pl.wedel.szzti.exception.ValidationException;
import pl.wedel.szzti.validation.ItemDtoValidation;

@RunWith(MockitoJUnitRunner.class)
public class ItemDtoValidationTest {

  private ItemDtoValidation itemDtoValidation;

  private ItemDto itemDto;

  @Before
  public void setUp() throws Exception {
    itemDtoValidation = new ItemDtoValidation();

    itemDto = ItemDto.builder()
        .dateOfDelivery(LocalDate.now())
        .insideType(InsideType.M.name())
        .itemType(ItemType.FURNITURE.name())
        .description("TEST")
        .equipment(true)
        .inventoryCode("123Test")
        .parent(null)
        .build();
  }

  @Test
  public void shouldNotThrowExceptionWhenItemDtoIsValid() {
    //given
    UUID itemId = UUID.randomUUID();
    itemDto.setId(itemId);

    //when
    itemDtoValidation.validateItem(itemId, itemDto);

    //then
  }

  @Test(expected = ValidationException.class)
  public void shouldThrowExceptionWhenIdMismatch() {
    //given
    itemDto.setId(UUID.randomUUID());

    //when
    itemDtoValidation.validateItem(UUID.randomUUID(), itemDto);

    //then
  }


  @Test(expected = ValidationException.class)
  public void shouldThrowExceptionWhenIdInsideTypeIsWrong() {
    //given
    UUID itemId = UUID.randomUUID();
    itemDto.setId(itemId);
    itemDto.setInsideType("TEST");

    //when
    itemDtoValidation.validateItem(itemId, itemDto);

    //then
  }


  @Test(expected = ValidationException.class)
  public void shouldThrowExceptionWhenItemTypeIsWrong() {
    //given
    itemDto.setId(UUID.randomUUID());
    itemDto.setItemType("TEST");
    //when
    itemDtoValidation.validateItem(UUID.randomUUID(), itemDto);

    //then
  }

}
