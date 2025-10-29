package usg.lostlink.server.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media extends AuditData {

    private String id;

    private byte[] data; // base64 format when sent to/from API

    private String mimeType; // e.g., image/jpeg, image/png
}
