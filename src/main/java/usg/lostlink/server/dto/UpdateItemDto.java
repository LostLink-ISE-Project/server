package usg.lostlink.server.dto;

import lombok.Data;
import usg.lostlink.server.enums.ItemStatus;

@Data
public class UpdateItemDto {

  private ItemStatus status;
}
