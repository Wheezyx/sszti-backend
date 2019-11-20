package pl.wedel.szzti.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wedel.szzti.domain.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, UUID>,
    SearchItemRepository {

  List<Item> findAllByIdIn(List<UUID> ids);
}

