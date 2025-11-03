package usg.lostlink.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.entity.Item;

import java.util.ArrayList;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {

}
