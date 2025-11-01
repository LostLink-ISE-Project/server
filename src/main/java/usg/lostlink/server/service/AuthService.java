package usg.lostlink.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.LoginDto;
import usg.lostlink.server.repository.AuthRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private AuthRepository authRepository;

    public LoginDto login(LoginDto loginDto) {
        LoginDto login = new LoginDto(loginDto.getUsername(), loginDto.getPassword());
        authRepository.save(login);
        return login;
    }
}
