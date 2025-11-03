package usg.lostlink.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDto {
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
}
