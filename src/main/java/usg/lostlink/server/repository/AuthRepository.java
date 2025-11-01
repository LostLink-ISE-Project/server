package usg.lostlink.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import usg.lostlink.server.dto.LoginDto;

@Repository
public interface AuthRepository extends CrudRepository<LoginDto, String> {

}
