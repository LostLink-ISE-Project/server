package usg.lostlink.server.dto;

import lombok.Getter;
import lombok.Setter;
import usg.lostlink.server.entity.Location;
import usg.lostlink.server.enums.ItemStatus;

@Getter
@Setter
public class ItemDto {


    private String itemName;
    private String itemDescription;
    private Location foundLocation;
    private String submitterEmail;
    private String image;

    public ItemDto(String itemName,
                   String itemDescription,
                   Location foundLocation,
                   String submitterEmail,
                   String image) {

        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.foundLocation = foundLocation;
        this.submitterEmail = submitterEmail;
        this.image = image;
    }

}