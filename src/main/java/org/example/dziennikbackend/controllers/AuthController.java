package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.UserDTO;
import org.example.dziennikbackend.models.UserEntity;
import org.example.dziennikbackend.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/users")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity _user) {
        UserEntity userEntity = authService.registerUser(_user);
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> loginUser(@RequestBody UserDTO _user) {
        UserEntity user = authService.validateCredentials(_user);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
