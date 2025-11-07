package usg.lostlink.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCurrentUserDto {
    private String name;
    private String surname;
    private String profilePhoto;
}
