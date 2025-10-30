package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.repository.ItemRepository;
import usg.lostlink.server.service.ItemService;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private ItemService itemService;


    @PostMapping()
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        ItemDto item = itemService.createItem(itemDto);
        return ResponseEntity.ok().body(item);
    }

    /*
    @GetMapping("/all")
    public String getListedItems() {
        // ArrayList<ItemDto> listedItems = itemService.getListedItems();
        return new String("salam");
    }
    */

}
