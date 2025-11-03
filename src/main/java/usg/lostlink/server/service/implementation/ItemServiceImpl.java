package usg.lostlink.server.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.entity.Item;
import usg.lostlink.server.enums.ItemStatus;
import usg.lostlink.server.repository.ItemRepository;
import usg.lostlink.server.service.ItemService;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {


    private final ItemRepository itemRepository;

    @Override
    public Item createItem(ItemDto itemDto) {
        Item item = Item.of( itemDto.getImage(),
                        itemDto.getItemName(),
                        itemDto.getItemDescription(),
                        itemDto.getFoundLocation(),
                        itemDto.getSubmitterEmail(),
                        ItemStatus.SUBMITTED,
                        itemDto.getGivenLocation());

        itemRepository.save(item);
        return item;
    }

}
