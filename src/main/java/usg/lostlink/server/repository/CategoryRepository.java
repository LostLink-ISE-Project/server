package usg.lostlink.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usg.lostlink.server.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
