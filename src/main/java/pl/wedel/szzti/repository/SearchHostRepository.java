package pl.wedel.szzti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.wedel.szzti.domain.Host;
import pl.wedel.szzti.web.HostSearchParameters;

public interface SearchHostRepository {

  Page<Host> search(HostSearchParameters searchParameters, Pageable pageable);
}
