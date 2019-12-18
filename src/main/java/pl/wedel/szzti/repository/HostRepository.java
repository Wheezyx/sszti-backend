package pl.wedel.szzti.repository;

import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wedel.szzti.domain.Host;

public interface HostRepository extends PagingAndSortingRepository<Host, UUID>,
    SearchHostRepository {

}
