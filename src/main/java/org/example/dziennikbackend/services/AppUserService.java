package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.AppUserDTO;
import org.example.dziennikbackend.models.DTOs.AttendanceDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.example.dziennikbackend.utils.DTOMapper;
import org.example.dziennikbackend.utils.ResourceConflictException;
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

    /**
     * obsluguje polecenie o podanie danych wszystkich użytkowników aplikacji
     * @return {@link List<AppUserDTO>} lista użytkowników systemu bez pola "password"
     */
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

    /**
     * odwoluje sie do bazy danych po uzytkownika o danym "id"
     * @param id {@link Long} numer id uzytkownika
     * @return {@link AppUserDTO} Obiekt uzytkownika
     * @throws ResourceNotFoundException blad pojawijacy sie gdy nie ma uzytkownika o danym id
     */
    @Transactional
    public AppUserDTO getUserById(Long id) throws ResourceNotFoundException {
        Optional<AppUser> user = appUserRepository.findById(id);
        AppUser appUser = user.orElse(null);
        if(appUser == null){
            throw new ResourceNotFoundException("Error while getting user: " + id.toString());
        }
        appUser.setPassword(null);
        return DTOMapper.map(appUser, AppUserDTO.class);
    }

    /**
     * dodaj nowego uzytkownika do bazy danych
     * @param user {@link AppUserDTO} nowy uzytkownik
     * @return {@link AppUserDTO} utworzonego uzytkownika bez pola "password"
     */
    @Transactional
    public AppUserDTO createUser(AppUserDTO user){
        AppUser appUser = DTOMapper.map(user, AppUser.class);
        appUser.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser newUser = appUserRepository.save(appUser);
        newUser.setPassword(null);
        return DTOMapper.map(newUser, AppUserDTO.class);
    }

    /**
     * znajduje uzytkownika po loginie
     * @param login {@link String} login uzytkownika
     * @return {@link AppUserDTO} uzytkownik
     * @throws ResourceNotFoundException
     */
    @Transactional
    public AppUserDTO getUserByLogin(String login) throws ResourceNotFoundException {
        AppUser user = appUserRepository.findByLogin(login).orElse(null);
        if(user == null){
            throw new ResourceNotFoundException("User not found: " + login);
        }
        AppUserDTO userDTO = DTOMapper.map(user, AppUserDTO.class);
        userDTO.setPassword(null);
        return userDTO;
    }

    /**
     * porownuje hasla na podstawie loginu uzytkownika
     * @param login {@link String} login uzytkownika
     * @param password {@link String} hasło do sprawdzenia
     * @return {@link Boolean} czy hasla są takie same
     * @throws ResourceNotFoundException gdy uzytkownik o padanym loginie nie istnieje
     */
    @Transactional
    public Boolean comparePassword(String login, String password) throws ResourceNotFoundException {
        AppUser user = appUserRepository.findByLogin(login).orElse(null);
        if(user == null){
            throw new ResourceNotFoundException("User not found: " + login);
        }
        if(passwordEncoder.matches(password, user.getPassword())){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * updatuje haslo uzytkownika w bazie danych
     * @param id {@link Long} numer id uzytkownika
     * @param oldPassword {@link String} stare haslo
     * @param newPassword {@link String} nowe haslo
     * @return {@link AppUserDTO} obiekt uzytkownika dla ktorego haslo zostalo zmienione
     * @throws ResourceConflictException gdy wystąpi blad z haslami
     * @throws ResourceNotFoundException gdy nie znaleziono uzytkownika
     */
    @Transactional
    public AppUserDTO updatePassword(Long id, String oldPassword, String newPassword) throws ResourceNotFoundException, ResourceConflictException{
        AppUser appUser = appUserRepository.findById(id).orElse(null);
        if(appUser == null){
            throw new ResourceNotFoundException("Error while getting user: " + id.toString());
        }
        if (oldPassword == null || passwordEncoder.matches(newPassword, oldPassword) || newPassword == null){
            throw new ResourceConflictException("Incorrect passwords (passwords cannot match or be empty): " + id.toString());
        }
        appUser.setPassword(passwordEncoder.encode(newPassword));
        AppUser updatedUser = appUserRepository.save(appUser);
        updatedUser.setPassword(null);
        return DTOMapper.map(updatedUser, AppUserDTO.class);
    }

    /**
     * usuwa uzytkownika
     * @param id {@link Long} number id uzytkownika
     */
    @Transactional
    public void deleteUser(Long id){
        appUserRepository.deleteById(id);
    }
}
