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

    private String category;

    public static Item of(String image,
                          String itemName,
                          String itemDescription,
                          String foundLocation,
                          String submitterEmail,
                          String givenLocation,
                          String category) {
        Item item = new Item();
        item.setImage(image);
        item.setItemName(itemName);
        item.setItemDescription(itemDescription);
        item.setFoundLocation(foundLocation);
        item.setSubmitterEmail(submitterEmail);
        item.setGivenLocation(givenLocation);
        item.setCategory(category);
        item.setCreatedDate(new Date());
        item.setUpdatedDate(new Date());

        return item;
    }
}
// extend eleyende nece olur table ve ofisin idsine gore yoxsa adna gore store 
