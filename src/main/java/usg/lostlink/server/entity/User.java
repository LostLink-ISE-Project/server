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

    private Date createdDate;

    private Date updatedDate;
}
