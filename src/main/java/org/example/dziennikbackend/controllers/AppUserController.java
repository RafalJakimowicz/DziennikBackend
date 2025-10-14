package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.AppUserDTO;
import org.example.dziennikbackend.models.DTOs.JwtTokenDTO;
import org.example.dziennikbackend.models.DTOs.PasswordsDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.services.AppUserService;
import org.example.dziennikbackend.services.AuthService;
import org.example.dziennikbackend.utils.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/users")
@CrossOrigin("http://localhost:4200")
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
    public ResponseEntity<AppUserDTO> getUser (@RequestBody JwtTokenDTO token) throws Exception {
        AppUserDTO user = authService.getUserByLogin(token);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id){
        AppUserDTO user;
        try {
            user = appUserService.getUserById(id);
        }
        catch (ResourceNotFoundException rnfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<AppUserDTO> createUser(@RequestBody AppUserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.createUser(user));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable Long id, @RequestBody PasswordsDTO upDTO) {
        AppUserDTO user;
        try {
            user = appUserService.updatePassword(id, upDTO.getOldPassword(), upDTO.getNewPassword());
        }
        catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.notFound().body(rnfe.toString());
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
