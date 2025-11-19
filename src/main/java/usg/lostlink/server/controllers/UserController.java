package usg.lostlink.server.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usg.lostlink.server.dto.CreateUserDto;
import usg.lostlink.server.dto.UpdateUserDto;
import usg.lostlink.server.entity.User;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.implementation.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<User>>> getUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(ApiResponse.success(users, "Users retrieved", HttpStatus.OK));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<User>> createUser(@RequestBody CreateUserDto dto) {
    User user = userService.createUser(dto);
    return ResponseEntity.ok(ApiResponse.success(user, "User created", HttpStatus.CREATED));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id,
                                                      @RequestBody UpdateUserDto dto) {
    User updated = userService.updateUser(id, dto);
    return ResponseEntity.ok(ApiResponse.success(updated, "User updated", HttpStatus.OK));
  }

  @PatchMapping("/status/{id}")
  public ResponseEntity<ApiResponse<Object>> changeUserStatus(@PathVariable Long id) {
    userService.changeUserStatus(id);
    return ResponseEntity.ok(ApiResponse.success(null, "User disabled", HttpStatus.OK));
  }
}
