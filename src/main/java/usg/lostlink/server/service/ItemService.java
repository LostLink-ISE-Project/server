package usg.lostlink.server.service;

import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.dto.UpdateItemStatusDto;
import usg.lostlink.server.entity.Item;

import java.util.List;

@Service
public interface ItemService {
    Item createItem(ItemDto itemDto);
    public boolean isAuthenticatedAdmin();
    public List<Item> getItemsByStatus(String statusStr);
    public List<?> getItems(boolean fullRequested);

    public Object getItemById(Long id,boolean full);

    Item updateItemStatus(Long itemId, UpdateItemStatusDto dto);

    public void deleteItem(Long itemId);
}

