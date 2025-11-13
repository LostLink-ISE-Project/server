package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usg.lostlink.server.dto.LoginDto;
import usg.lostlink.server.dto.ResetPasswordDto;
import usg.lostlink.server.dto.UpdateCurrentUserDto;
import usg.lostlink.server.dto.UpdateUserDto;
import usg.lostlink.server.entity.User;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.implementation.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;
    // Public: login
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDto loginDto) {
        String jwtToken = authService.login(loginDto);
        return ResponseEntity.ok(
                ApiResponse.success(jwtToken, "Login successful", HttpStatus.OK)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<User>> getCurrentUser() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(
                ApiResponse.success(user, "User data retrieved", HttpStatus.OK)
        );
    }

    @PatchMapping("/me")
    public ResponseEntity<ApiResponse<User>> updateCurrentUser(@RequestBody UpdateCurrentUserDto updateUserDto) {
        User updatedUser = authService.updateCurrentUser(updateUserDto);
        return ResponseEntity.ok(
                ApiResponse.success(updatedUser, "User profile updated", HttpStatus.OK)
        );
    }

    @PostMapping("/me/reset-password")
    public ResponseEntity<ApiResponse<Object>> resetPassword(@RequestBody ResetPasswordDto resetDto) {
        authService.resetPassword(resetDto);
        return ResponseEntity.ok(
                ApiResponse.success(null, "Password successfully updated", HttpStatus.OK)
        );
    }
}