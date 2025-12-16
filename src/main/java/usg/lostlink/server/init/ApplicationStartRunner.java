package usg.lostlink.server.init;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import usg.lostlink.server.entity.User;
import usg.lostlink.server.enums.UserStatus;
import usg.lostlink.server.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements CommandLineRunner {

  private final UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {

    String adminUsername = "admin";

    if (userRepository.findByUsername(adminUsername).isEmpty()) {

      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

      User admin = new User();
      admin.setUsername(adminUsername);
      admin.setPassword(encoder.encode("admin123"));
      admin.setName("System");
      admin.setSurname("Administrator");
      admin.setStatus(UserStatus.ACTIVE);
      admin.setCreatedDate(new Date());
      admin.setUpdatedDate(new Date());

      userRepository.save(admin);

      System.out.println("✅ Admin user created");

    } else {
      System.out.println("ℹ️ Admin user already exists");
    }
  }
}
