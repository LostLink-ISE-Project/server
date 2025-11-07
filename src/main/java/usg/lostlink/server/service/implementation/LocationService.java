package usg.lostlink.server.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.LocationDto;
import usg.lostlink.server.entity.Location;
import usg.lostlink.server.repository.LocationRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

  private final LocationRepository locationRepository;

  private String getCurrentUsername() {
    var auth = SecurityContextHolder.getContext().getAuthentication();
    return auth != null ? auth.getName() : "anonymousUser";
  }

  public List<Location> getAll() {
    return (List<Location>) locationRepository.findAll();
  }

  public Location create(LocationDto dto) {
    Location location = new Location();
    location.setSlug(dto.getSlug());
    location.setName(dto.getName());
    location.setDescription(dto.getDescription());
    location.setCreatedDate(new Date());
    location.setUpdatedDate(new Date());
    location.setCreatedBy(getCurrentUsername());
    location.setUpdatedBy(getCurrentUsername());
    return locationRepository.save(location);
  }

  public Location update(Long id, LocationDto dto) {
    Location location = locationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Location not found"));
    location.setSlug(dto.getSlug());
    location.setName(dto.getName());
    location.setDescription(dto.getDescription());
    location.setUpdatedDate(new Date());
    location.setUpdatedBy(getCurrentUsername());
    return locationRepository.save(location);
  }

  public void delete(Long id) {

    locationRepository.deleteById(id);
  }

  public Location getLocationByID(Long id) {
    return locationRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
  }
}
