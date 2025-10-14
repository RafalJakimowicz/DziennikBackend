package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.AppUserDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.example.dziennikbackend.utils.DTOMapper;
import org.example.dziennikbackend.utils.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<AppUserDTO> getAllUsers(){
        List<AppUser> users =  appUserRepository.findAll();
        List<AppUserDTO> userDTOs = new ArrayList<>();
        for (AppUser user : users) {
            user.setPassword(null);
            userDTOs.add(DTOMapper.map(user, AppUserDTO.class));
        }
        return userDTOs;
    }

    @Transactional
    public AppUserDTO getUserById(Long id) throws ResourceNotFoundException {
        Optional<AppUser> user = appUserRepository.findById(id);
        AppUser appUser = user.orElse(null);
        if(appUser == null){
            throw new ResourceNotFoundException("Get User: " + id.toString());
        }
        appUser.setPassword(null);
        return DTOMapper.map(appUser, AppUserDTO.class);
    }

    @Transactional
    public AppUserDTO createUser(AppUserDTO user){
        AppUser appUser = DTOMapper.map(user, AppUser.class);
        appUser.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser newUser = appUserRepository.save(appUser);
        newUser.setPassword(null);
        return DTOMapper.map(newUser, AppUserDTO.class);
    }

    @Transactional
    public AppUserDTO updatePassword(Long id, String oldPassword, String newPassword) throws ResourceNotFoundException {
        if (oldPassword == null || passwordEncoder.matches(newPassword, oldPassword) || newPassword == null){
            return null;
        }
        Optional<AppUser> userOptional = appUserRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new ResourceNotFoundException("Update User Password: " + id.toString());
        }
        AppUser user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        AppUser updatedUser = appUserRepository.save(user);
        updatedUser.setPassword(null);
        return DTOMapper.map(updatedUser, AppUserDTO.class);
    }

    @Transactional
    public void deleteUser(Long id){
        appUserRepository.deleteById(id);
    }
}
