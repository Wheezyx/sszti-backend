package pl.wedel.szzti.validation;

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

@RunWith(MockitoJUnitRunner.class)
public class ItemDtoValidatorTest {

  private ItemDtoValidator itemDtoValidator;

  private ItemDto itemDto;

  @Before
  public void setUp() throws Exception {
    itemDtoValidator = new ItemDtoValidator();

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
    itemDtoValidator.validateItem(itemId, itemDto);

    //then
  }

  @Test(expected = ValidationException.class)
  public void shouldThrowExceptionWhenIdMismatch() {
    //given
    itemDto.setId(UUID.randomUUID());

    //when
    itemDtoValidator.validateItem(UUID.randomUUID(), itemDto);

    //then
  }


  @Test(expected = ValidationException.class)
  public void shouldThrowExceptionWhenIdInsideTypeIsWrong() {
    //given
    UUID itemId = UUID.randomUUID();
    itemDto.setId(itemId);
    itemDto.setInsideType("TEST");

    //when
    itemDtoValidator.validateItem(itemId, itemDto);

    //then
  }


  @Test(expected = ValidationException.class)
  public void shouldThrowExceptionWhenItemTypeIsWrong() {
    //given
    itemDto.setId(UUID.randomUUID());
    itemDto.setItemType("TEST");
    //when
    itemDtoValidator.validateItem(UUID.randomUUID(), itemDto);

    //then
  }

}
