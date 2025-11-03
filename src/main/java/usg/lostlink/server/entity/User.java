package usg.lostlink.server.entity;

import jakarta.persistence.*;
import lombok.*;
import usg.lostlink.server.enums.UserStatus;

import java.util.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profilePhoto;

    private String name;

    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    private UserStatus status;

    private Date createdDate;

    private Date updatedDate;


}
