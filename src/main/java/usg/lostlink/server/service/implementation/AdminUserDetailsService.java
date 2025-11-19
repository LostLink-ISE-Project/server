package usg.lostlink.server.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import usg.lostlink.server.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .map(admin -> User.builder()
            .username(admin.getUsername())
            .password(admin.getPassword())
            .roles("ADMIN")
            .build()
        ).orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
  }
}
