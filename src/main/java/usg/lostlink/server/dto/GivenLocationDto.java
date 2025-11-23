package usg.lostlink.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GivenLocationDto {

  private String name;
  private String location;
  private String workHours;
  private String contact;
}
