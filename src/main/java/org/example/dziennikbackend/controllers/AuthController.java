package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.configs.JwtUtil;
import org.example.dziennikbackend.models.DTOs.AppUserDTO;
import org.example.dziennikbackend.models.DTOs.AuthDTO;
import org.example.dziennikbackend.models.DTOs.JwtTokenDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtTokenDTO> registerUser(@RequestBody AppUserDTO _user) throws Exception {
        AppUserDTO appUser = authService.registerUser(_user);
        if (appUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = jwtUtil.generateToken(new AuthDTO(_user.getLogin(), _user.getPassword()), 24);
        return new ResponseEntity<>(new JwtTokenDTO(token), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> loginUser(@RequestBody AuthDTO _user) throws Exception {
        AppUserDTO user = authService.validateCredentials(_user);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = jwtUtil.generateToken(_user, 24);
        return new ResponseEntity<>(new JwtTokenDTO(token), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<JwtTokenDTO> logoutUser (@RequestBody JwtTokenDTO token) throws Exception {
        jwtUtil.revokeToken(token.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
