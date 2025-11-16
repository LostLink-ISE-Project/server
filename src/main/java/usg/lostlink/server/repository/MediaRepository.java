package usg.lostlink.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usg.lostlink.server.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, String> {

}
