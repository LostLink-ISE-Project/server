package usg.lostlink.server.mapper;

import usg.lostlink.server.dto.GivenLocationDto;
import usg.lostlink.server.dto.PublicItemDto;
import usg.lostlink.server.entity.Item;

public class PublicItemMapper {

  public static PublicItemDto mapToPublicItemDto(Item item) {

    GivenLocationDto dto = new GivenLocationDto();
    return new PublicItemDto(

        item.getId(),
        item.getItemName(),
        item.getItemDescription(),
        item.getFoundLocation(),
        dto,
        item.getImage(),
        item.getItemStatus(),
        item.getCreatedDate(),
        item.getCategory()
    );
  }

//  public static CreatedItemDto toCreatedItemDto(Item item) {
//    CreatedItemDto dto = new CreatedItemDto();
//    dto.setItemId(item.getId());
//    dto.setItemName(item.getItemName());
//    dto.setItemDescription(item.getItemDescription());
//    dto.setFoundLocation(item.getFoundLocation());
//    dto.setImage(item.getImage());
//    dto.setGivenLocation(item.getGivenLocation());
//    dto.setItemStatus(item.getItemStatus());
//    dto.setCreatedAt(item.getCreatedDate());
//    return dto;
//  }
}
