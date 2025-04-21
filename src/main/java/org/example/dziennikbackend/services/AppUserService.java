package org.example.dziennikbackend.services;

import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AppUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }

    public AppUser getUserById(Long id){
        Optional<AppUser> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public AppUser createUser(AppUser user){
        return userRepository.save(user);
    }

    public AppUser updateUser(Long id, AppUser user){
        Optional<AppUser> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            return null;
        }
        userOptional.get().setSurname(user.getSurname());
        userOptional.get().setLogin(user.getLogin());
        userOptional.get().setName(user.getName());
        userOptional.get().setEmail(user.getEmail());
        userOptional.get().setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(userOptional.get());
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
