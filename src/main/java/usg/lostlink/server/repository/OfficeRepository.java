package usg.lostlink.server.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import usg.lostlink.server.entity.Office;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Long> {

  Optional<Office> findByName(String name);
}
