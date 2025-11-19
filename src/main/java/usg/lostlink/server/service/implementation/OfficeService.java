package usg.lostlink.server.service.implementation;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.OfficeDto;
import usg.lostlink.server.entity.Office;
import usg.lostlink.server.repository.OfficeRepository;

@Service
@RequiredArgsConstructor
public class OfficeService {

  private final OfficeRepository officeRepository;

  public List<Office> getAll() {
    return (List<Office>) officeRepository.findAll();
  }

  public Office create(OfficeDto dto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = ((UserDetails) authentication.getPrincipal()).getUsername();

    Office office = new Office();
    office.setName(dto.getName());
    office.setLocation(dto.getLocation());
    office.setWorkHours(dto.getWorkHours());
    office.setContact(dto.getContact());
    office.setCreatedDate(new Date());
    office.setUpdatedDate(new Date());
    office.setCreatedBy(username);
    office.setUpdatedBy(username);
    officeRepository.save(office);
    return office;
  }

  public Office update(Long id, OfficeDto dto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = ((UserDetails) authentication.getPrincipal()).getUsername();

    Office office = officeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Office not found"));
    office.setName(dto.getName());
    office.setLocation(dto.getLocation());
    office.setWorkHours(dto.getWorkHours());
    office.setContact(dto.getContact());
    office.setUpdatedBy(username);
    office.setUpdatedDate(new Date());
    return officeRepository.save(office);
  }

  public void delete(Long id) {
    officeRepository.deleteById(id);
  }

  public Office getOfficeById(Long id) {
    return officeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Office not found with id: " + id));
  }

}
