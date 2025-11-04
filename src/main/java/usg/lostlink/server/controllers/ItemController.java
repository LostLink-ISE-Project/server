package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.entity.Item;
import usg.lostlink.server.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/v1/items")
@RequiredArgsConstructor
public class ItemController {


    private final ItemService itemService;



    @PostMapping()
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto) {
        Item item = itemService.createItem(itemDto);
        return ResponseEntity.ok().body(item);
    }

    @GetMapping()
    public ResponseEntity<List<?>> getItems(@RequestParam(defaultValue = "false") boolean full) {
        return ResponseEntity.ok(itemService.getItems(full));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean full) {
        return ResponseEntity.ok(itemService.getItemById(id,full));
    }

}
