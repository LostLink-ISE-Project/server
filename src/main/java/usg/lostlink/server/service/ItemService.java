package usg.lostlink.server.service;

import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.entity.Item;

@Service
public interface ItemService {
    Item createItem(ItemDto itemDto);
}
