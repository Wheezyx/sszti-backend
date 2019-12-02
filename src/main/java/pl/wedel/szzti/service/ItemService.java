package pl.wedel.szzti.service;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.NotFoundException;
import pl.wedel.szzti.repository.ItemRepository;
import pl.wedel.szzti.web.ItemSearchParameters;

@Service
@AllArgsConstructor
@Slf4j
public class ItemService {

  private final ItemRepository itemRepository;

  public Page<Item> search(ItemSearchParameters searchParameters, Pageable pageable) {
    log.debug("Finding items.");
    return itemRepository.search(searchParameters, pageable);
  }

  public Item findById(UUID id) {
    log.debug("Finding item with id {}", id);
    return itemRepository.findById(id).orElseThrow(() -> new NotFoundException(
        new ErrorMessage(String.format("Item with id %s not found.", id.toString()))));
  }

  public List<Item> findAllByIds(List<UUID> itemIds) {
    return this.itemRepository.findAllByIdIn(itemIds);
  }

  public Item save(Item item) {
    log.debug("Saving item");
    return itemRepository.save(item);
  }

  public Item update(Item item) {
    log.debug("Updating item with id: {}", item.getId());
    return itemRepository.save(item);
  }

  public void deleteById(UUID itemId) {
    log.debug("Removing item with id: {}", itemId);
    this.itemRepository.deleteById(itemId);
  }
}
