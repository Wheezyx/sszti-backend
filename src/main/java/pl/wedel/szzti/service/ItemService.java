package pl.wedel.szzti.service;

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

@Service
@AllArgsConstructor
@Slf4j
public class ItemService {

  private final ItemRepository itemRepository;

  public Page<Item> findAll(Pageable pageable) {
    log.debug("Finding all items.");
    return itemRepository.findAll(pageable);
  }

  public Item findById(UUID id) {
    log.debug("Finding item with id {}", id);
    return itemRepository.findById(id).orElseThrow(() -> new NotFoundException(
        new ErrorMessage(String.format("Item with id %s not found.", id.toString()))));
  }

  public Item save(Item item) {
    log.debug("Saving item");
    return itemRepository.save(item);
  }

}
