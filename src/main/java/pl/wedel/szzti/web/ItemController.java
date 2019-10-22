package pl.wedel.szzti.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.dto.ItemDto;
import pl.wedel.szzti.service.ItemService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

  private final ItemService itemService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<ItemDto> getItems(Pageable pageable) {
    return itemService.findAll(pageable).map(this::toDto);
  }

  private ItemDto toDto(Item item) {
    ItemDto itemDto = ItemDto.builder()
        .placeOfPosting(item.getPlaceOfPosting())
        .insideType(item.getInsideType())
        .equipment(item.isEquipment())
        .inventoryCode(item.getInventoryCode())
        .itemType(item.getItemType())
        .fullItemName(item.getFullItemName())
        .description(item.getDescription())
        .genericName(item.getGenericName().getName())
        .dateOfDelivery(item.getDateOfDelivery())
        .build();
    itemDto.setId(item.getId());
    return itemDto;
  }
}
