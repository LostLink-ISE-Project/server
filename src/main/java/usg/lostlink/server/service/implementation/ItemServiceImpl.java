package usg.lostlink.server.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.entity.Item;
import usg.lostlink.server.enums.ItemStatus;
import usg.lostlink.server.repository.ItemRepository;
import usg.lostlink.server.repository.UserRepository;
import usg.lostlink.server.service.ItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {


    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

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

    public boolean isAuthenticatedAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if someone is authenticated
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        // Principal must be your UserDetails implementation
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            // Since you said any valid user is admin, return true if user exists in DB
            return userRepository.findByUsername(userDetails.getUsername()).isPresent();
        }

        return false;
    }

    public List<Item> getItemsByStatus(String statusStr) {
        ItemStatus status;
        try {
            status = ItemStatus.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + statusStr);
        }

        return itemRepository.findByItemStatus(status);
    }

    public List<Item> getPublicItems() {
        // Return only approved/visible items
        return itemRepository.findByItemStatus(ItemStatus.LISTED);
    }


}
