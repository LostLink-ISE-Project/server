package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usg.lostlink.server.dto.GeneralReportDto;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.implementation.ReportService;


@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getReport(
            @RequestParam(required = false) String period,
            @RequestParam String scope) {

        GeneralReportDto result = reportService.getReport(period, scope);
        return ResponseEntity.ok(ApiResponse.success(result, "Report generated", HttpStatus.OK));
    }
}
