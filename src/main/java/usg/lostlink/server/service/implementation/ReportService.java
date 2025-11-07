package usg.lostlink.server.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.GeneralReportDto;
import usg.lostlink.server.enums.ItemStatus;
import usg.lostlink.server.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ItemRepository itemRepository;

    public GeneralReportDto getGeneralReport() {
        long total = itemRepository.count();
        long found = itemRepository.countByItemStatus(ItemStatus.LISTED);
        long claimed = itemRepository.countByItemStatus(ItemStatus.CLAIMED);
        long archived = itemRepository.countByItemStatus(ItemStatus.ARCHIVED);

        return new GeneralReportDto(total, found, claimed, archived);
    }
}
