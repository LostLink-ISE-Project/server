package usg.lostlink.server.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import usg.lostlink.server.entity.User;
import usg.lostlink.server.enums.UserStatus;
import usg.lostlink.server.repository.UserRepository;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements CommandLineRunner {

    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

/*
        User newUser = new User();
        newUser.setProfilePhoto("https://example.com/photos/default.jpg");
        newUser.setName("Rasul");
        newUser.setSurname("Mohsumov");
        newUser.setUsername("valeh");
        newUser.setPassword(passwordEncoder.encode("valeh")); // Always encode passwords!
        newUser.setStatus(UserStatus.ACTIVE); // Assuming you have an enum UserStatus with ACTIVE

        newUser.setCreatedDate(new Date());
        newUser.setUpdatedDate(new Date());
        userRepository.save(newUser);
        System.out.println(newUser.toString());

*/
    }
}
