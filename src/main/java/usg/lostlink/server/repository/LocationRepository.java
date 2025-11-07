package usg.lostlink.server.repository;

import org.springframework.data.repository.CrudRepository;
import usg.lostlink.server.entity.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
