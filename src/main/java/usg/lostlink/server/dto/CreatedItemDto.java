package usg.lostlink.server.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import usg.lostlink.server.enums.ItemStatus;

@Getter
@Setter
public class CreatedItemDto {

  private Long itemId;
  private String itemName;
  private String itemDescription;
  private String foundLocation;
  private String image;
  private String givenLocation;
  private ItemStatus itemStatus;
  private Date createdAt;
}
