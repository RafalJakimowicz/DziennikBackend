package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.configs.JwtUtil;
import org.example.dziennikbackend.models.DTOs.AppUserDTO;
import org.example.dziennikbackend.models.DTOs.AuthDTO;
import org.example.dziennikbackend.models.DTOs.JwtTokenDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.example.dziennikbackend.utils.DTOMapper;
import org.example.dziennikbackend.utils.ResourceNotFoundException;
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
    public AppUserDTO validateCredentials(AuthDTO authDTO) throws ResourceNotFoundException {
        Optional<AppUser> user = appUserRepository.findByLogin(authDTO.getLogin());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Validate Credentials: " + authDTO.getLogin());
        }
        if (!passwordEncoder.matches(authDTO.getPassword(), user.get().getPassword())) {
            return null;
        }
        return DTOMapper.map(user.get(), AppUserDTO.class);
    }

    @Transactional
    public AppUserDTO registerUser(AppUserDTO user){
        Optional<AppUser> newUser = appUserRepository.findByLogin(user.getLogin());
        if (newUser.isPresent()) {
            return null;
        }
        newUser = Optional.of(appUserRepository.save(DTOMapper.map(user, AppUser.class)));
        newUser.get().setPassword(passwordEncoder.encode(user.getPassword()));
        return DTOMapper.map(newUser, AppUserDTO.class);
    }

    @Transactional
    public AppUserDTO getUserByLogin(JwtTokenDTO token) throws Exception {
        String username = jwtUtil.extractUsernameFromToken(token.getToken());
        Optional<AppUser> user = appUserRepository.findByLogin(username);
        return user.map(appUser -> DTOMapper.map(appUser, AppUserDTO.class)).orElse(null);
    }
}
