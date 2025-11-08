package usg.lostlink.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.entity.Item;
import usg.lostlink.server.enums.ItemStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {
    long countByItemStatus(ItemStatus status);

    List<Item> findByItemStatus(ItemStatus status);

    Item findItemById(Long id);

    List<Item> findByCreatedDateBeforeAndItemStatusNot(Date threeMonthsAgo, ItemStatus itemStatus);

    List<Item> findByCreatedDateBefore(java.util.Date twelveMonthsAgo);

    Long countByCreatedDateBetween(Date from, Date to);
}

