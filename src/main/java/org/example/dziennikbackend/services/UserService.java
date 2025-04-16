package org.example.dziennikbackend.services;

import org.example.dziennikbackend.models.UserDTO;
import org.example.dziennikbackend.models.UserEntity;
import org.example.dziennikbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity validateCredentials(UserDTO userDTO) {
        Optional<UserEntity> user = userRepository.findByLogin(userDTO.getLogin());
        if (user.isEmpty()) {
            return null;
        }
        if (!user.get().getPassword().equals(
                passwordEncoder.encode(userDTO.getPassword())
            )
        ) {
            return null;
        }
        user.get().setPassword(null);
        return user.get();
    }

    public UserEntity registerUser(UserEntity user){
        Optional<UserEntity> newUser = userRepository.findByLogin(user.getLogin());
        if (newUser.isPresent()) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
