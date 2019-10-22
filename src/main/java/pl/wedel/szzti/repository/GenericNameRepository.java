package pl.wedel.szzti.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import pl.wedel.szzti.domain.GenericName;

public interface GenericNameRepository extends CrudRepository<GenericName, UUID> {

}
