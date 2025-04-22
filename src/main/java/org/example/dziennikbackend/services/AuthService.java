package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.configs.JwtUtil;
import org.example.dziennikbackend.models.DTOs.AuthDTO;
import org.example.dziennikbackend.models.DTOs.JwtTokenDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public AppUser validateCredentials(AuthDTO authDTO) {
        Optional<AppUser> user = appUserRepository.findByLogin(authDTO.getLogin());
        if (user.isEmpty()) {
            return null;
        }
        if (!passwordEncoder.matches(authDTO.getPassword(), user.get().getPassword())){
            return null;
        }
        user.get().setPassword(null);
        return user.get();
    }

    @Transactional
    public AppUser registerUser(AppUser user){
        Optional<AppUser> newUser = appUserRepository.findByLogin(user.getLogin());
        if (newUser.isPresent()) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser = Optional.of(appUserRepository.save(user));
        newUser.get().setPassword(null);
        return newUser.get();
    }

    @Transactional
    public AppUser getUserByLogin(JwtTokenDTO token){
        String username = jwtUtil.extractUsernameFromToken(token.getToken());
        Optional<AppUser> user = appUserRepository.findByLogin(username);
        if (user.isEmpty()) {
            return null;
        }
        user.get().setPassword(null);
        return user.get();
    }
}
