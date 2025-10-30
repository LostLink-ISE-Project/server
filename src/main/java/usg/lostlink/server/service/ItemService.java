package usg.lostlink.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.repository.ItemRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    public ItemDto createItem(ItemDto itemDto) {
        ItemDto item = new ItemDto(itemDto.getItemName(),
                itemDto.getItemDescription(),
                itemDto.getFoundLocation(),
                itemDto.getSubmitterEmail(),
                itemDto.getImage()
        );
        itemRepository.save(item);
        return item;
    }

    public ArrayList<ItemDto> getListedItems() {
        ArrayList<ItemDto> listedItems = itemRepository.getListedItems();
        return listedItems;
    }
}
