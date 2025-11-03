package usg.lostlink.server.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import usg.lostlink.server.enums.ItemStatus;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String itemName;

    private String itemDescription;

    private String foundLocation;

    private String submitterEmail;

    private String givenLocation;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    //User.username
    private String createdBy;
    //User.username
    private String updatedBy;

    public static Item of(String image,
                          String itemName,
                          String itemDescription,
                          String foundLocation,
                          String submitterEmail,
                          ItemStatus itemStatus,
                          String givenLocation) {
        Item item = new Item();
        item.setImage(image);
        item.setItemName(itemName);
        item.setItemDescription(itemDescription);
        item.setFoundLocation(foundLocation);
        item.setSubmitterEmail(submitterEmail);
        item.setItemStatus(itemStatus);
        item.setGivenLocation(givenLocation);
        item.setCreatedDate(new Date());
        item.setUpdatedDate(new Date());
        item.setCreatedBy("system");
        item.setUpdatedBy("system");
        return item;
    }
}
// extend eleyende nece olur table ve ofisin idsine gore yoxsa adna gore store 
