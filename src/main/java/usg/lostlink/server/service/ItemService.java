package usg.lostlink.server.service;

import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.entity.Item;

import java.util.List;

@Service
public interface ItemService {
    Item createItem(ItemDto itemDto);
    public boolean isAuthenticatedAdmin();
    public List<Item> getItemsByStatus(String statusStr);
    public List<Item> getPublicItems();
}
