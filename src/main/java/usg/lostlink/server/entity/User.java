package usg.lostlink.server.entity;

import jakarta.persistence.*;
import lombok.*;
import usg.lostlink.server.enums.UserStatus;

import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User{

    private String id;

    private String profilePhoto;

    private String name;

    private String surname;

    private String username;

    private String password;

    private UserStatus status;
    // User classi Auditi extend ede bilmez cunki Auditin fieldlerinde User subclassi istifade olunur.
    // Onun yerine 2 dene date fieldini Auditden bura elave edirem.
    private Date createdDate;

    private Date updatedDate;
}
