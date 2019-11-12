package pl.wedel.szzti.repository;

import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wedel.szzti.domain.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, UUID>,
    SearchItemRepository {

}
