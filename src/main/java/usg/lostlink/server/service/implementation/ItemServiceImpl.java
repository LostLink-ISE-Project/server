package usg.lostlink.server.service.implementation;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.*;
import usg.lostlink.server.entity.Item;
import usg.lostlink.server.entity.User;
import usg.lostlink.server.enums.ItemStatus;
import usg.lostlink.server.repository.ItemRepository;
import usg.lostlink.server.repository.UserRepository;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.ItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {


    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<Object> createItem(ItemDto itemDto) {
        Item item = Item.of( itemDto.getImage(),
                        itemDto.getItemName(),
                        itemDto.getItemDescription(),
                        itemDto.getFoundLocation(),
                        itemDto.getSubmitterEmail(),
                        itemDto.getGivenLocation());

        // ✅ Get current authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isAuthenticatedAdmin()) {

            // ✅ Authenticated admin — set audit fields
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            Optional<User> user = userRepository.findByUsername(username);

            user.ifPresent(u -> {
                        item.setCreatedBy(u.getUsername());
                        item.setUpdatedBy(u.getUsername());
                        item.setItemStatus(ItemStatus.LISTED);
            });
            return ApiResponse.success(null,"Item has been created, and status is set to SUBMITTED.", HttpStatus.CREATED);
        }
        else{
            item.setCreatedBy("system");
            item.setUpdatedBy("system");
            item.setItemStatus(ItemStatus.SUBMITTED);
        }


        itemRepository.save(item);
        return ApiResponse.success(null,"Item has been created.", HttpStatus.CREATED);
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

    public List<?> getItems(boolean fullRequested) {
        boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (isAuthenticated && fullRequested) {
            // Admin: full access to all items
            return (List<Item>) itemRepository.findAll();
        } else {
            // Public user: only listed items, mapped to public DTO
            List<Item> listedItems = itemRepository.findByItemStatus(ItemStatus.LISTED);

            return listedItems.stream()
                    .map(item -> new PublicItemDto(
                            item.getItemName(),
                            item.getItemDescription(),
                            item.getFoundLocation(),
                            item.getGivenLocation(),
                            item.getImage(),
                            item.getItemStatus(),
                            item.getCreatedDate()
                    ))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Object getItemById(Long id ,boolean full) {
        boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (isAuthenticated && full) {
            // Admin: full access to all items
            return itemRepository.findItemById(id);
        } else {
            // Public user: only listed items, mapped to public DTO
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            PublicItemDto dto = new PublicItemDto(
                    item.getItemName(),
                    item.getItemDescription(),
                    item.getFoundLocation(),
                    item.getGivenLocation(),
                    item.getImage(),
                    item.getItemStatus(),
                    item.getCreatedDate());
            return dto;
        }
    }


    @Override
    public void updateItemStatus(Long itemId, UpdateItemStatusDto dto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));

        // Get current authenticated username from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        item.setItemStatus(dto.getStatus());
        item.setUpdatedBy(username);
        item.setUpdatedDate(new Date());

        itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Item not found"));

        if (item.getItemStatus() != ItemStatus.SUBMITTED) {
            throw new IllegalStateException("Only items with status SUBMITTED can be deleted.");
        }

        itemRepository.delete(item);
    }


}
