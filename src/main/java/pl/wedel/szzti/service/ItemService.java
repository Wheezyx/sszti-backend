package pl.wedel.szzti.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.repository.ItemRepository;

@Service
@AllArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public Page<Item> findAll(Pageable pageable) {
    return itemRepository.findAll(pageable);
  }

}
