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
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

  private final LocationService locationService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<Location>>> getAll() {
    return ResponseEntity.ok(ApiResponse.success(locationService.getAll(), "Locations retrieved", HttpStatus.OK));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Location>> getById(@PathVariable Long id) {
    Location location = locationService.getLocationByID(id);
    return ResponseEntity.ok(ApiResponse.success(location, "Location retrieved", HttpStatus.OK));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Location>> create(@RequestBody LocationDto dto) {
    Location created = locationService.create(dto);
    return ResponseEntity.ok(ApiResponse.success(created, "Location created", HttpStatus.CREATED));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ApiResponse<Location>> update(@PathVariable Long id, @RequestBody LocationDto dto) {
    Location updated = locationService.update(id, dto);
    return ResponseEntity.ok(ApiResponse.success(updated, "Location updated", HttpStatus.OK));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
    locationService.delete(id);
    return ResponseEntity.ok(ApiResponse.success(null, "Location deleted", HttpStatus.OK));
  }
}
