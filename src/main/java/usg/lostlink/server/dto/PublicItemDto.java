package usg.lostlink.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import usg.lostlink.server.enums.ItemStatus;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicItemDto {
    private Long id;
    private String itemName;
    private String itemDescription;
    private String foundLocation;
    private String givenLocation;
    private String image;
    private ItemStatus status;
    private Date createdDate;
    private String category;
}
