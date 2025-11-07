package usg.lostlink.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    private String id;

    private byte[] data; // base64 format when sent to/from API

    private String mimeType;

    private Date createdDate;

    private Date updatedDate;

    private User createdBy;

    private User updatedBy;
}
