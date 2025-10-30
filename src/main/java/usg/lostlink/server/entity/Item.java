package usg.lostlink.server.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import usg.lostlink.server.enums.ItemStatus;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends AuditData {

    private String id;

    private String image;

    private String itemName;

    private String itemDescription;

    private String foundLocation;

    private String submitterEmail;

    private Office givenlocation;

    private ItemStatus itemStatus;
}
