package usg.lostlink.server.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import usg.lostlink.server.enums.ItemStatus;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends AuditData {

    @Id
    private String id;

    private String image;

    private String itemName;

    private String itemDescription;

    private String foundLocation;

    private String submitterEmail;

    private String givenlocation;

    private ItemStatus itemStatus;
}
// extend eleyende nece olur table ve ofisin idsine gore yoxsa adna gore store 
