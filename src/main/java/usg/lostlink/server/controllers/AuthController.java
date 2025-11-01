package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usg.lostlink.server.dto.LoginDto;
import usg.lostlink.server.service.AuthService;

@RestController
@RequestMapping("/v1/auth/login")
@RequiredArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto) {
        LoginDto result = authService.login(loginDto);
        return ResponseEntity.ok().body(result);
    }
}
