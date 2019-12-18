package pl.wedel.szzti.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wedel.szzti.domain.ConnectionInterface;

public interface ConnectionInterfaceRepository extends
    PagingAndSortingRepository<ConnectionInterface, UUID> {

  Page<ConnectionInterface> findAllByHostId(UUID id, Pageable pageable);
}
