package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
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

    @Transactional
    public List<AppUser> getAllUsers(){
        List<AppUser> users =  appUserRepository.findAll();
        for (AppUser user : users) {
            user.setPassword(null);
        }
        return users;
    }

    @Transactional
    public AppUser getUserById(Long id){
        Optional<AppUser> user = appUserRepository.findById(id);
        AppUser appUser = user.orElse(null);
        if(appUser == null){
            return null;
        }
        appUser.setPassword(null);
        return appUser;
    }

    @Transactional
    public AppUser createUser(AppUser user){
        AppUser appUser = appUserRepository.save(user);
        appUser.setPassword(null);
        return appUser;
    }

    @Transactional
    public AppUser updateUser(Long id, AppUser user){
        Optional<AppUser> userOptional = appUserRepository.findById(id);
        if(userOptional.isEmpty()){
            return null;
        }
        if (user.getLogin() != null) {
            userOptional.get().setLogin(user.getLogin());
        } if (user.getName() != null) {
            userOptional.get().setName(user.getName());
        } if (user.getEmail() != null) {
            userOptional.get().setEmail(user.getEmail());
        } if (user.getSurname() != null) {
            userOptional.get().setSurname(user.getSurname());
        }
        AppUser savedUser = appUserRepository.save(userOptional.get());
        savedUser.setPassword(null);
        return savedUser;
    }

    @Transactional
    public AppUser updatePassword(Long id, String oldPassword, String newPassword){
        if (oldPassword == null || passwordEncoder.matches(newPassword, oldPassword) || newPassword == null){
            return null;
        }
        Optional<AppUser> userOptional = appUserRepository.findById(id);
        if(userOptional.isEmpty()){
            return null;
        }
        AppUser user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        AppUser updatedUser = appUserRepository.save(user);
        updatedUser.setPassword(null);
        return updatedUser;
    }

    @Transactional
    public void deleteUser(Long id){
        appUserRepository.deleteById(id);
    }
}
