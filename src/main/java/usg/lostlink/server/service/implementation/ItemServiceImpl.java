package usg.lostlink.server.service.implementation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.dto.UpdateItemStatusDto;
import usg.lostlink.server.entity.Item;
import usg.lostlink.server.entity.User;
import usg.lostlink.server.enums.ItemStatus;
import usg.lostlink.server.mapper.PublicItemMapper;
import usg.lostlink.server.repository.ItemRepository;
import usg.lostlink.server.repository.MediaRepository;
import usg.lostlink.server.repository.UserRepository;
import usg.lostlink.server.service.ItemService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final UserRepository userRepository;
  private final MediaRepository mediaRepository;

  @Override
  public void createItem(ItemDto itemDto) {
    Item item = Item.of(itemDto.getImage(),
        itemDto.getItemName(),
        itemDto.getItemDescription(),
        itemDto.getFoundLocation(),
        itemDto.getSubmitterEmail(),
        itemDto.getGivenLocation(),
        itemDto.getCategory());

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (isAuthenticatedAdmin()) {
      String username = ((UserDetails) authentication.getPrincipal()).getUsername();
      Optional<User> user = userRepository.findByUsername(username);

      user.ifPresent(u -> {
        item.setCreatedBy(u.getUsername());
        item.setUpdatedBy(u.getUsername());
        item.setItemStatus(ItemStatus.LISTED);
      });
    } else {
      item.setCreatedBy("system");
      item.setUpdatedBy("system");
      item.setItemStatus(ItemStatus.SUBMITTED);
    }
    itemRepository.save(item);
  }

  @Override
  public List<?> getItems(boolean fullRequested, ItemStatus status) {
    boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null
        && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
        && !"anonymousUser".equals(
        SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    if (isAuthenticated) {
      // Admin: full access to all items. if full = true then fetch all,
      // if not true then fetch by status
      if (fullRequested) {
        return itemRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));

      } else {
        return itemRepository.findByItemStatus(status,
            Sort.by(Sort.Direction.DESC, "createdDate"));
      }

    } else {
      // Public user: only listed items, mapped to public DTO
      List<Item> listedItems = itemRepository.findByItemStatus(ItemStatus.LISTED,
          Sort.by(Sort.Direction.DESC, "createdDate"));

      return listedItems.stream()
          .map(PublicItemMapper::mapToPublicItemDto)
          .collect(Collectors.toList());
    }
  }

  @Override
  public Object getItemById(Long id) {
    boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null
        && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
        && !"anonymousUser".equals(
        SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    if (isAuthenticated) {
      // Admin: full access to all items
      return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    } else {
      // Public user: only listed items, mapped to public DTO
      Item item = itemRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Item not found"));

      return PublicItemMapper.mapToPublicItemDto(item);
    }
  }

  @Override
  public void updateItemStatus(Long itemId, UpdateItemStatusDto dto) {
    Item item = itemRepository.findById(itemId)
        .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = ((UserDetails) authentication.getPrincipal()).getUsername();

    item.setItemStatus(dto.getStatus());
    item.setUpdatedBy(username);
    item.setUpdatedDate(new Date());

    itemRepository.save(item);
  }

  @Override
  @Transactional
  public void deleteItem(Long itemId) {
    Item item = itemRepository.findById(itemId)
        .orElseThrow(() -> new RuntimeException("Item not found"));

    if (item.getItemStatus() != ItemStatus.SUBMITTED) {
      throw new IllegalStateException("Only items with status SUBMITTED can be deleted.");
    }

    mediaRepository.deleteById(item.getImage());

    itemRepository.delete(item);
  }

  // Run every day at 5 AM (Asia/Baku timezone)
  @Scheduled(cron = "0 0 5 * * *", zone = "Asia/Baku")
  public void archiveAndDeleteItems() {
    log.info("Starting scheduled archive & delete task...");

    final Date threeMonthsAgo = Date.from(LocalDate.now()
        .minusMonths(3)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant());

    final Date twelveMonthsAgo = Date.from(LocalDate.now()
        .minusMonths(12)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant());

    //Archive items older than 3 months (but not already archived or deleted)
    List<Item> toArchive =
        itemRepository.findByCreatedDateBeforeAndItemStatusNot(threeMonthsAgo, ItemStatus.ARCHIVED);
    toArchive.forEach(item -> item.setItemStatus(ItemStatus.ARCHIVED));
    itemRepository.saveAll(toArchive);
    log.info("Archived {} items", toArchive.size());

    //Delete items older than 12 months
    List<Item> toDelete = itemRepository.findByCreatedDateBefore(twelveMonthsAgo);
    itemRepository.deleteAll(toDelete);
    log.info("Deleted {} items", toDelete.size());

    log.info("Finished scheduled archive & delete task.");
  }

  public boolean isAuthenticatedAdmin() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      return false;
    }
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails userDetails) {
      return userRepository.findByUsername(userDetails.getUsername()).isPresent();
    }

    return false;
  }

}
