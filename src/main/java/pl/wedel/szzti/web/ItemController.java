package pl.wedel.szzti.web;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wedel.szzti.domain.InsideType;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.domain.ItemType;
import pl.wedel.szzti.dto.ItemDto;
import pl.wedel.szzti.service.ItemService;
import pl.wedel.szzti.validation.ItemDtoValidation;

@RestController
@AllArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

  private final ItemService itemService;

  private final ItemDtoValidation itemDtoValidation;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<ItemDto> getItems(Pageable pageable) {
    return itemService.findAll(pageable).map(this::toDto);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ItemDto getIem(@PathVariable("id") UUID itemId) {
    Item item = itemService.findById(itemId);
    return toDto(item);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ItemDto saveItem(@RequestBody ItemDto itemDto) {
    //TODO ADD DTO validation
    Item item = fromDto(itemDto);
    return toDto(itemService.save(item));
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ItemDto updateItem(@PathVariable("id") UUID itemId, @RequestBody ItemDto itemDto) {
    itemDtoValidation.validateItem(itemId, itemDto);
    Item item = fromDto(itemDto);
    return toDto(itemService.update(item));
  }

  //TODO Export methods to dedicated class - mapper
  private ItemDto toDto(Item item) {
    ItemDto itemDto = ItemDto.builder()
        .placeOfPosting(item.getPlaceOfPosting())
        .insideType(item.getInsideType().name())
        .equipment(item.isEquipment())
        .inventoryCode(item.getInventoryCode())
        .itemType(item.getItemType().name())
        .fullItemName(item.getFullItemName())
        .description(item.getDescription())
        .genericName(item.getGenericName() != null ? item.getGenericName().getName() : null)
        .dateOfDelivery(item.getDateOfDelivery())
        .build();
    itemDto.setId(item.getId());
    return itemDto;
  }

  private Item fromDto(ItemDto itemDto) {
    return Item.builder()
        .placeOfPosting(itemDto.getPlaceOfPosting())
        .insideType(InsideType.fromString(itemDto.getInsideType()))
        .equipment(itemDto.isEquipment())
        .inventoryCode(itemDto.getInventoryCode())
        .itemType(ItemType.fromString(itemDto.getItemType()))
        .fullItemName(itemDto.getFullItemName())
        .description(itemDto.getDescription())
        //TODO ADD GENERIC NAME FINDING BY NAME AND ADDING HERE.
        .dateOfDelivery(itemDto.getDateOfDelivery())
        .build();
  }
}
