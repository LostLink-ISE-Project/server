package usg.lostlink.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeneralReportDto {

  private long totalSubmissions;
  private long foundItems;
  private long claimedItems;
  private long archivedItems;
}
