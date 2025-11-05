package usg.lostlink.server.repository;

import org.springframework.data.repository.CrudRepository;
import usg.lostlink.server.entity.Office;

public interface OfficeRepository extends CrudRepository<Office, Long> {
}
