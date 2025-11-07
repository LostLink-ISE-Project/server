package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usg.lostlink.server.dto.GeneralReportDto;
import usg.lostlink.server.service.implementation.ReportService;


@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<?> getReport(@RequestParam String scope) {
        if ("general".equalsIgnoreCase(scope)) {
            GeneralReportDto dto = reportService.getGeneralReport();
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().body("Unsupported scope: " + scope);
    }
}
