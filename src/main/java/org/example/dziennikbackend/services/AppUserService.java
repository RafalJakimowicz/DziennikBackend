package org.example.dziennikbackend.services;

import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    public AppUser getUserById(Long id){
        Optional<AppUser> user = appUserRepository.findById(id);
        return user.orElse(null);
    }

    public AppUser createUser(AppUser user){
        return appUserRepository.save(user);
    }

    public Long getUserIdByLogin(String login){
        Optional<AppUser> user = appUserRepository.findByLogin(login);
        if(user.isEmpty()){
            return null;
        }
        return user.get().getId();
    }

    public AppUser updateUser(Long id, AppUser user){
        Optional<AppUser> userOptional = appUserRepository.findById(id);
        if(userOptional.isEmpty()){
            return null;
        }
        userOptional.get().setSurname(user.getSurname());
        userOptional.get().setLogin(user.getLogin());
        userOptional.get().setName(user.getName());
        userOptional.get().setEmail(user.getEmail());
        userOptional.get().setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepository.save(userOptional.get());
    }

    public void deleteUser(Long id){
        appUserRepository.deleteById(id);
    }
}
