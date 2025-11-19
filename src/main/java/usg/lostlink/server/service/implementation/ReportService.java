package usg.lostlink.server.service.implementation;

import java.time.LocalDate;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.GeneralReportDto;
import usg.lostlink.server.enums.ItemStatus;
import usg.lostlink.server.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ReportService {

  private final ItemRepository itemRepository;

  public GeneralReportDto getReport(String period, String scope) {
    LocalDate start = null;
    LocalDate end = null;

    // Parse date range from ?period=YYYY-MM-DD_YYYY-MM-DD
    if (period != null && period.contains("_")) {
      String[] parts = period.split("_");
      start = LocalDate.parse(parts[0]);
      end = LocalDate.parse(parts[1]);
    }

    if ("general".equalsIgnoreCase(scope)) {
      return generateGeneralReport(start, end);
    }

    throw new IllegalArgumentException("Unsupported report scope: " + scope);
  }

  private GeneralReportDto generateGeneralReport(LocalDate start, LocalDate end) {
    Date from = start != null ? java.sql.Date.valueOf(start) : null;
    Date to = end != null ? java.sql.Date.valueOf(end) : null;

    long total = (from != null && to != null)
        ? itemRepository.countByCreatedDateBetween(from, to)
        : itemRepository.count();

    long listed = itemRepository.countByItemStatus(ItemStatus.LISTED);
    long claimed = itemRepository.countByItemStatus(ItemStatus.CLAIMED);
    long archived = itemRepository.countByItemStatus(ItemStatus.ARCHIVED);

    return new GeneralReportDto(total, listed, claimed, archived);
  }

}
