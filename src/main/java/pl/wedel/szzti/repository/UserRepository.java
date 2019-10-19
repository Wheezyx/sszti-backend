package pl.wedel.szzti.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import pl.wedel.szzti.domain.User;

public interface UserRepository extends CrudRepository<User, UUID> {

  Optional<User> findByUsername(String username);

}
