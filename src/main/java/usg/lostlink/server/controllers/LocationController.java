package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import usg.lostlink.server.dto.LocationDto;
import usg.lostlink.server.entity.Location;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.implementation.LocationService;

import java.util.List;

@RestController
@RequestMapping("/v1/locations")
@RequiredArgsConstructor
public class LocationController {

  private final LocationService locationService;

  // ✅ GET all (public)
  @GetMapping
  public ResponseEntity<ApiResponse<List<Location>>> getAll() {
    return ResponseEntity.ok(ApiResponse.success(locationService.getAll(), "Locations retrieved", HttpStatus.OK));
  }

  // ✅ POST (admin only)
  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<ApiResponse<Location>> create(@RequestBody LocationDto dto) {
    Location created = locationService.create(dto);
    return ResponseEntity.ok(ApiResponse.success(created, "Location created", HttpStatus.CREATED));
  }

  // ✅ PATCH by ID (admin only)
  @PatchMapping("/{id}")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<ApiResponse<Location>> update(@PathVariable Long id, @RequestBody LocationDto dto) {
    Location updated = locationService.update(id, dto);
    return ResponseEntity.ok(ApiResponse.success(updated, "Location updated", HttpStatus.OK));
  }

  // ✅ DELETE by ID (admin only)
  @DeleteMapping("/{id}")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
    locationService.delete(id);
    return ResponseEntity.ok(ApiResponse.success(null, "Location deleted", HttpStatus.OK));
  }
}
