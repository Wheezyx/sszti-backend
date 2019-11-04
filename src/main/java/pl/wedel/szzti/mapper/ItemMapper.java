package pl.wedel.szzti.mapper;

import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.InsideType;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.domain.ItemType;
import pl.wedel.szzti.dto.ItemDto;

@Service
public class ItemMapper {

  public ItemDto toDto(Item item) {
    if (null == item) {
      return null;
    }
    ItemDto itemDto = ItemDto.builder()
        .placeOfPosting(item.getPlaceOfPosting())
        .insideType(item.getInsideType() != null ? item.getInsideType().name() : null)
        .equipment(item.isEquipment())
        .inventoryCode(item.getInventoryCode())
        .itemType(item.getInsideType() != null ? item.getInsideType().name() : null)
        .fullItemName(item.getFullItemName())
        .description(item.getDescription())
        .genericName(item.getGenericName() != null ? item.getGenericName().getName() : null)
        .dateOfDelivery(item.getDateOfDelivery())
        .build();
    itemDto.setId(item.getId());
    return itemDto;
  }

  public Item fromDto(ItemDto itemDto) {
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
