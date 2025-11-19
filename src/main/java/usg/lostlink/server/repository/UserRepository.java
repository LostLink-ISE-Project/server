package usg.lostlink.server.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import usg.lostlink.server.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
