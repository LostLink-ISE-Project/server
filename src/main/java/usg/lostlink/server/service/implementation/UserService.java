package usg.lostlink.server.service.implementation;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.CreateUserDto;
import usg.lostlink.server.dto.UpdateUserDto;
import usg.lostlink.server.entity.User;
import usg.lostlink.server.enums.UserStatus;
import usg.lostlink.server.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  public User createUser(CreateUserDto dto) {
    User user = new User();
    user.setName(dto.getName());
    user.setSurname(dto.getSurname());
    user.setUsername(dto.getUsername());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    user.setStatus(UserStatus.ACTIVE);
    user.setCreatedDate(new Date());
    return userRepository.save(user);
  }

  public User updateUser(Long id, UpdateUserDto dto) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    user.setName(dto.getName());
    user.setSurname(dto.getSurname());
    user.setUpdatedDate(new Date());
    return userRepository.save(user);
  }

  public void disableUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    user.setStatus(UserStatus.DISABLED);
    user.setUpdatedDate(new Date());
    userRepository.save(user);
  }
}
