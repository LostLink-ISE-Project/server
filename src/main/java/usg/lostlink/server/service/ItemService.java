package usg.lostlink.server.service;

import java.util.List;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.dto.UpdateItemStatusDto;
import usg.lostlink.server.enums.ItemStatus;

@Service
public interface ItemService {

  void createItem(ItemDto itemDto);

  boolean isAuthenticatedAdmin();

  List<?> getItems(boolean fullRequested, ItemStatus status);

  Object getItemById(Long id);

  void updateItemStatus(Long itemId, UpdateItemStatusDto dto);

  void deleteItem(Long itemId);
}

