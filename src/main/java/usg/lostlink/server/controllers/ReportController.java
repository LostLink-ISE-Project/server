package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usg.lostlink.server.dto.GeneralReportDto;
import usg.lostlink.server.dto.PublicReportDto;
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

  @GetMapping("/public")
  public ResponseEntity<ApiResponse<?>> getReport(
      @RequestParam(required = false) String period) {

    PublicReportDto result = reportService.getPublicReport(period);
    return ResponseEntity.ok(ApiResponse.success(result, "Report generated", HttpStatus.OK));
  }
}
