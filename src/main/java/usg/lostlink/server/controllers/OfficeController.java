package usg.lostlink.server.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usg.lostlink.server.dto.OfficeDto;
import usg.lostlink.server.entity.Office;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.implementation.OfficeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offices")
public class OfficeController {

  private final OfficeService officeService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<Office>>> getAll() {
    List<Office> offices = officeService.getAll();
    return ResponseEntity.ok(ApiResponse.success(offices, "Offices retrieved", HttpStatus.OK));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Office>> getById(@PathVariable Long id) {
    Office office = officeService.getOfficeById(id);
    return ResponseEntity.ok(ApiResponse.success(office, "Office retrieved", HttpStatus.OK));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Office>> create(@RequestBody OfficeDto dto) {
    Office created = officeService.create(dto);
    return ResponseEntity.ok(ApiResponse.success(created, "Office created", HttpStatus.CREATED));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ApiResponse<Office>> update(@PathVariable Long id,
                                                    @RequestBody OfficeDto dto) {
    Office updated = officeService.update(id, dto);
    return ResponseEntity.ok(ApiResponse.success(updated, "Office updated", HttpStatus.OK));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
    officeService.delete(id);
    return ResponseEntity.ok(ApiResponse.success(null, "Office deleted", HttpStatus.OK));
  }
}