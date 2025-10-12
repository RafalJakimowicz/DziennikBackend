package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.AppUserDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.AppUserRepository;
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
    public List<AppUserDTO> getAllUsers(){
        List<AppUser> users =  appUserRepository.findAll();
        List<AppUserDTO> userDTOs = new ArrayList<>();
        for (AppUser user : users) {
            user.setPassword(null);
            userDTOs.add(changeUserToDTO(user));
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
        return changeUserToDTO(appUser);
    }

    @Transactional
    public AppUserDTO createUser(AppUserDTO user){
        AppUser appUser = changeUserToEntity(user);
        appUser.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser newUser = appUserRepository.save(appUser);
        newUser.setPassword(null);
        return changeUserToDTO(newUser);
    }

    @Transactional
    public AppUserDTO updateUser(Long id, AppUserDTO user) throws ResourceNotFoundException {
        Optional<AppUser> userOptional = appUserRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new ResourceNotFoundException("Update User: " + id.toString());
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
        return changeUserToDTO(savedUser);
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
        return changeUserToDTO(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id){
        appUserRepository.deleteById(id);
    }
}
