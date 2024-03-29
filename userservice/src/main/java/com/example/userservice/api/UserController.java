package com.example.userservice.api;

import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users") public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @PostMapping("/users/save") public ResponseEntity<User>saveUser(@RequestBody User user){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PostMapping("/role/save") public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/role/addToUser") public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(),form.getRolename());
        return ResponseEntity.ok().build(); //build because UserService.addRoleToUser returns void
    }

}
@Data
class RoleToUserForm{
    private String username;
    private String rolename;
}