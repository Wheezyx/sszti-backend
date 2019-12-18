package pl.wedel.szzti.mapper;

import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.dto.ItemCsvDto;

@Service
public class ItemCsvMapper {

  public ItemCsvDto toDto(Item item) {
    if (null == item) {
      return null;
    }
    ItemCsvDto itemDto = ItemCsvDto.builder()
        .placeOfPosting(item.getPlaceOfPosting())
        .insideType(item.getInsideType())
        .equipment(item.isEquipment())
        .inventoryCode(item.getInventoryCode())
        .itemType(item.getItemType())
        .fullItemName(item.getFullItemName())
        .description(item.getDescription())
        .genericName(item.getGenericName())
        .dateOfDelivery(item.getDateOfDelivery())
        .build();
    itemDto.setParentName(item.getParent() != null ? item.getParent().getFullItemName() : "");
    itemDto.setId(item.getId());

    setParentNameAndRenterCode(itemDto, item);
    return itemDto;
  }

  private void setParentNameAndRenterCode(ItemCsvDto itemDto, Item item) {
    if (item.getRental() == null) {
      return;
    }
    if (item.getRental().getPlace() != null) {
      itemDto.setPlaceName(item.getRental().getPlace().getName());
    }
    if (item.getRental().getRenter() != null) {
      itemDto.setRenterCode(item.getRental().getRenter().getCode());
    }
  }
}
