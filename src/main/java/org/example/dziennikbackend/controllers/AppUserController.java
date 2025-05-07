package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.AppUserDTO;
import org.example.dziennikbackend.models.DTOs.JwtTokenDTO;
import org.example.dziennikbackend.models.DTOs.PasswordsDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.services.AppUserService;
import org.example.dziennikbackend.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/users")
public class AppUserController {
    private final AppUserService appUserService;
    private final AuthService authService;

    public AppUserController(AppUserService appUserService, AuthService authService) {
        this.appUserService = appUserService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<AppUserDTO>> getAllUsers() {
        List<AppUserDTO> users = appUserService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(users);
    }

    @PostMapping("/me")
    public ResponseEntity<AppUser> getUser (@RequestBody JwtTokenDTO token) throws Exception {
        AppUser user = authService.getUserByLogin(token);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id) {
        AppUserDTO user = appUserService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<AppUserDTO> createUser(@RequestBody AppUserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Long id, @RequestBody AppUserDTO user) {
        AppUserDTO updatedUser = appUserService.updateUser(id, user);
        if(updatedUser == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<AppUserDTO> updateUserPassword(@PathVariable Long id, @RequestBody PasswordsDTO upDTO) {
        AppUserDTO user = appUserService.updatePassword(id, upDTO.getOldPassword(), upDTO.getNewPassword());
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
