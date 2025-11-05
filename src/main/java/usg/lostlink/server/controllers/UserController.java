package usg.lostlink.server.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import usg.lostlink.server.service.implementation.UserService;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody CreateUserDto dto) {
    return ResponseEntity.ok(userService.createUser(dto));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id,
                                         @RequestBody UpdateUserDto dto) {
    return ResponseEntity.ok(userService.updateUser(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> disableUser(@PathVariable Long id) {
    userService.disableUser(id);
    return ResponseEntity.noContent().build();
  }
}
