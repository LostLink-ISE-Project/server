package usg.lostlink.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

  private String itemName;
  private String itemDescription;
  private String foundLocation;
  private String submitterEmail;
  private String image;
  private String givenLocation;
  private String category;

  public ItemDto(String itemName,
                 String itemDescription,
                 String foundLocation,
                 String submitterEmail,
                 String image,
                 String givenLocation,
                 String category) {

    this.itemName = itemName;
    this.itemDescription = itemDescription;
    this.foundLocation = foundLocation;
    this.submitterEmail = submitterEmail;
    this.image = image;
    this.givenLocation = givenLocation;
    this.category = category;
  }

}