package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.dto.UpdateItemStatusDto;
import usg.lostlink.server.enums.ItemStatus;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {


    private final ItemService itemService;



    @PostMapping()
    public ApiResponse<Object> createItem(@RequestBody ItemDto itemDto) {
        return itemService.createItem(itemDto);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<?>>> getItems(@RequestParam(defaultValue = "false") boolean full,
                                            @RequestParam(defaultValue = "LISTED") ItemStatus status) {
        return ResponseEntity.ok(ApiResponse.success(itemService.getItems(full,status),"Items retrieved.",HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getItemById(@PathVariable Long id ) {
        return ResponseEntity.ok(ApiResponse.success(itemService.getItemById(id), "Item retrieved.", HttpStatus.OK));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updateItemStatus(@PathVariable Long id,
                                                                @RequestBody UpdateItemStatusDto dto) {
        itemService.updateItemStatus(id, dto);
        return ResponseEntity.ok(ApiResponse.success(null, "Item's status updated.", HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Item deleted", HttpStatus.NO_CONTENT));
    }


}
