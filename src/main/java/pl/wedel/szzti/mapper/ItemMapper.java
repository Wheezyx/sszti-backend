package pl.wedel.szzti.mapper;

import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.dto.ItemDto;

@Service
public class ItemMapper {

  public ItemDto toDto(Item item) {
    if (null == item) {
      return null;
    }
    ItemDto itemDto = ItemDto.builder()
        .placeOfPosting(item.getPlaceOfPosting())
        .insideType(item.getInsideType())
        .equipment(item.isEquipment())
        .inventoryCode(item.getInventoryCode())
        .itemType(item.getItemType())
        .fullItemName(item.getFullItemName())
        .description(item.getDescription())
        .genericName(item.getGenericName())
        .dateOfDelivery(item.getDateOfDelivery())
        .rented(item.getRental() != null)
        .build();
    itemDto.setParent(toDto(item.getParent()));
    itemDto.setId(item.getId());
    return itemDto;
  }

  public Item fromDto(ItemDto itemDto) {
    Item item =  Item.builder()
        .placeOfPosting(itemDto.getPlaceOfPosting())
        .insideType(itemDto.getInsideType())
        .equipment(itemDto.isEquipment())
        .inventoryCode(itemDto.getInventoryCode())
        .itemType(itemDto.getItemType())
        .fullItemName(itemDto.getFullItemName())
        .description(itemDto.getDescription())
        .genericName(itemDto.getGenericName())
        .dateOfDelivery(itemDto.getDateOfDelivery())
        .build();
    item.setId(itemDto.getId());
    return item;
  }
}
