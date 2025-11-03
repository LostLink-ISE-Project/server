package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usg.lostlink.server.dto.LoginDto;
import usg.lostlink.server.dto.ResetPasswordDto;
import usg.lostlink.server.dto.UpdateUserDto;
import usg.lostlink.server.entity.User;
import usg.lostlink.server.service.implementation.AuthService;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String jwtToken = authService.login(loginDto);
        return ResponseEntity.ok().body(jwtToken);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }

    @PatchMapping("/me")
    public ResponseEntity<User> updateCurrentUser(@RequestBody UpdateUserDto updateUserDto) {
        User updatedUser = authService.updateCurrentUser(updateUserDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/me/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetDto) {
        authService.resetPassword(resetDto);
        return ResponseEntity.ok("Password successfully updated.");
    }
}