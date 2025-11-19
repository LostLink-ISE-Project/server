package usg.lostlink.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Media {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private byte[] data; // base64 format when sent to/from API

  private String mimeType;

  //private Date createdDate;

  //private Date updatedDate;

  //private User createdBy;

  //private User updatedBy;
}
