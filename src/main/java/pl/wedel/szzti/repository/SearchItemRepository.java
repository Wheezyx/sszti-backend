package pl.wedel.szzti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.web.ItemSearchParameters;

public interface SearchItemRepository {

  Page<Item> search(ItemSearchParameters searchParameters, Pageable pageable);

}
