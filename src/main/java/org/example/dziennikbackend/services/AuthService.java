package org.example.dziennikbackend.services;

import org.example.dziennikbackend.models.DTOs.AuthDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser validateCredentials(AuthDTO authDTO) {
        Optional<AppUser> user = userRepository.findByLogin(authDTO.getLogin());
        if (user.isEmpty()) {
            return null;
        }
        if (!passwordEncoder.matches(authDTO.getPassword(), user.get().getPassword())){
            return null;
        }
        user.get().setPassword(null);
        return user.get();
    }

    public AppUser registerUser(AppUser user){
        Optional<AppUser> newUser = userRepository.findByLogin(user.getLogin());
        if (newUser.isPresent()) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser = Optional.of(userRepository.save(user));
        newUser.get().setPassword(null);
        return newUser.get();
    }
}
