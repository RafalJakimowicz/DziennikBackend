package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.configs.JwtUtil;
import org.example.dziennikbackend.models.DTOs.AppUserDTO;
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

    private AppUserDTO changeUserToDTO(AppUser user) {
        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setSurname(user.getSurname());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }

    private AppUser changeUserToEntity(AppUserDTO userDTO) {
        AppUser user = new AppUser();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    @Transactional
    public AppUserDTO validateCredentials(AuthDTO authDTO) {
        Optional<AppUser> user = appUserRepository.findByLogin(authDTO.getLogin());
        if (user.isEmpty()) {
            return null;
        }
        if (!passwordEncoder.matches(authDTO.getPassword(), user.get().getPassword())) {
            return null;
        }
        return changeUserToDTO(user.get());
    }

    @Transactional
    public AppUserDTO registerUser(AppUserDTO user){
        Optional<AppUser> newUser = appUserRepository.findByLogin(user.getLogin());
        if (newUser.isPresent()) {
            return null;
        }
        newUser.get().setPassword(passwordEncoder.encode(user.getPassword()));
        newUser = Optional.of(appUserRepository.save(changeUserToEntity(user)));
        return changeUserToDTO(newUser.get());
    }

    @Transactional
    public AppUserDTO getUserByLogin(JwtTokenDTO token) throws Exception {
        String username = jwtUtil.extractUsernameFromToken(token.getToken());
        Optional<AppUser> user = appUserRepository.findByLogin(username);
        if (user.isEmpty()) {
            return null;
        }
        return changeUserToDTO(user.get());
    }
}
