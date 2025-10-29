package usg.lostlink.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usg.lostlink.server.dto.ItemDto;
import usg.lostlink.server.entity.Item;
import usg.lostlink.server.entity.Location;

@RestController
@RequestMapping("/v1/items")
public class ItemController {

    @PostMapping()
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        ItemDto item = new ItemDto(itemDto.getItemName(),
                itemDto.getItemDescription(),
                itemDto.getFoundLocation(),
                itemDto.getSubmitterEmail(),
                itemDto.getImage()
        );

        return ResponseEntity.ok().body(item);

    }
}
