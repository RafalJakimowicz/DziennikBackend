package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.configs.JwtUtil;
import org.example.dziennikbackend.models.DTOs.AppUserDTO;
import org.example.dziennikbackend.models.DTOs.AuthDTO;
import org.example.dziennikbackend.models.DTOs.JwtTokenDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.services.AuthService;
import org.example.dziennikbackend.utils.ErrorMessage;
import org.example.dziennikbackend.utils.ResourceConflictException;
import org.example.dziennikbackend.utils.ResourceNotFoundException;
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

    /**
     * Registers new user
     * @param _user {@link AppUserDTO} new user to be registered
     * @return {@link JwtTokenDTO} if registration is succesfull otherwise returns {@link ErrorMessage} with details
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUserDTO _user){
        try {
            AppUserDTO appUser = authService.registerUser(_user);
        }
        catch (ResourceConflictException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorMessage().getObject("Registering error", e.toString(), 400)
            );
        }
        String token = jwtUtil.generateToken(new AuthDTO(_user.getLogin(), _user.getPassword()), 24);
        return new ResponseEntity<>(new JwtTokenDTO(token), HttpStatus.CREATED);
    }

    /**
     * Login user
     * @param _user {@link AuthDTO} credentials to validate
     * @return {@link JwtTokenDTO} if user is successfully logged otherwise returns {@link ErrorMessage} with details
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthDTO _user){
        AppUserDTO user;
        try {
            user = authService.validateCredentials(_user);
        }
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(new ErrorMessage().getObject(
                            "Login Error",
                            e.toString(),
                            404
                    ));
        }
        String token = jwtUtil.generateToken(_user, 24);
        return new ResponseEntity<>(new JwtTokenDTO(token), HttpStatus.OK);
    }

    /**
     * Removes token from list of active tokens
     * @param token {@link JwtTokenDTO} Jwt token
     * @return {@link HttpStatus} Confirmation of success
     */
    @PostMapping("/logout")
    public ResponseEntity<JwtTokenDTO> logoutUser (@RequestBody JwtTokenDTO token){
        jwtUtil.revokeToken(token.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
