package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.UserRepository;
import org.example.dziennikbackend.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/private/users")
public class UserController {
    private final AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserService.getAllUsers();
        if (users.size() == 0){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser user = appUserService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.createUser(user));
    }

    @PutMapping
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user) {
        return ResponseEntity.ok(appUserService.createUser(user));
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody AppUser user) {
        appUserService.deleteUser(user.getId());
        return ResponseEntity.ok().build();
    }
}
