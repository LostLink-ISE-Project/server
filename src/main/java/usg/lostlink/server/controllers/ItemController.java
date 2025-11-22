package usg.lostlink.server.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.dto.UpdateItemStatusDto;
import usg.lostlink.server.enums.ItemStatus;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.ItemService;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @PostMapping()
  public ResponseEntity<ApiResponse<Object>> createItem(@RequestBody ItemDto itemDto) {
    itemService.createItem(itemDto);
    return ResponseEntity.ok(
        ApiResponse.success(null, "Item has been created, and status is set to SUBMITTED.",
            HttpStatus.CREATED));
  }

  @GetMapping()
  public ResponseEntity<ApiResponse<List<?>>> getItems(
      @RequestParam(defaultValue = "false") boolean full,
      @RequestParam(defaultValue = "LISTED") ItemStatus status) {
    return ResponseEntity.ok(
        ApiResponse.success(itemService.getItems(full, status), "Items retrieved.", HttpStatus.OK));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<?>> getItemById(@PathVariable Long id) {
    return ResponseEntity.ok(
        ApiResponse.success(itemService.getItemById(id), "Item retrieved.", HttpStatus.OK));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ApiResponse<Object>> updateItemStatus(@PathVariable Long id,
                                                              @RequestBody
                                                              UpdateItemStatusDto dto) {
    itemService.updateItemStatus(id, dto);
    return ResponseEntity.ok(ApiResponse.success(null, "Item's status updated.", HttpStatus.OK));
  }

  @DeleteMapping()
  public ResponseEntity<ApiResponse<Object>> deleteItems(@RequestBody List<Long> ids) {
    itemService.deleteItems(ids);
    return ResponseEntity.ok(ApiResponse.success(null, "Item deleted", HttpStatus.NO_CONTENT));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Object>> deleteItemById(@PathVariable Long id) {
    itemService.deleteItem(id);
    return ResponseEntity.ok(ApiResponse.success(null, "Item deleted", HttpStatus.NO_CONTENT));
  }

}
