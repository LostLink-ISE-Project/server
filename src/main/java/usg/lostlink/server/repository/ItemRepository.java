package usg.lostlink.server.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import usg.lostlink.server.entity.Item;
import usg.lostlink.server.enums.ItemStatus;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

  long countByItemStatus(ItemStatus status);

  //List<Item> findByItemStatus(ItemStatus status);
  List<Item> findByItemStatus(ItemStatus status, Sort sort);

  List<Item> findAll(Sort sort);

  Item findItemById(Long id);

  List<Item> findByCreatedDateBeforeAndItemStatusNot(Date threeMonthsAgo, ItemStatus itemStatus);

  List<Item> findByCreatedDateBefore(java.util.Date twelveMonthsAgo);

  Long countByCreatedDateBetween(Date from, Date to);

}

