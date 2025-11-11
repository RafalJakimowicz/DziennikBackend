package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.configs.JwtUtil;
import org.example.dziennikbackend.models.DTOs.AppUserDTO;
import org.example.dziennikbackend.models.DTOs.AuthDTO;
import org.example.dziennikbackend.models.DTOs.JwtTokenDTO;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.example.dziennikbackend.utils.DTOMapper;
import org.example.dziennikbackend.utils.ResourceConflictException;
import org.example.dziennikbackend.utils.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final AppUserService appUserService;

    public AuthService(JwtUtil jwtUtil,  AppUserService appUserService) {
        this.jwtUtil = jwtUtil;
        this.appUserService = appUserService;
    }

    /**
     * sprawdza dane logowania uzytkownika
     * @param authDTO {@link AuthDTO} dane logowania uzytkownika
     * @return {@link AppUserDTO} zalogowany uzytkownik
     * @throws ResourceNotFoundException gdy nie ma podanego uzytkownika
     * @throws ResourceConflictException gdy haslo nie jest poprawne
     */
    @Transactional
    public AppUserDTO validateCredentials(AuthDTO authDTO) throws ResourceNotFoundException, ResourceConflictException {
        Boolean ifmatch = false;
        try{
            ifmatch = appUserService.comparePassword(authDTO.getLogin(), authDTO.getPassword());
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        if (ifmatch) {
            return appUserService.getUserByLogin(authDTO.getLogin());
        }
        else{
            throw new ResourceConflictException("Password not match");
        }
    }

    /**
     * rejestruje nowego uzytkownika
     * @param user {@link AppUserDTO} uzytkownik
     * @return {@link AppUserDTO} nowy zajestrwany uzytkownik
     * @throws ResourceConflictException gdy uzytkownik juz istnieje
     */
    @Transactional
    public AppUserDTO registerUser(AppUserDTO user) throws  ResourceConflictException {
        Boolean isPresent = true;
        try{
            AppUserDTO existingUser = appUserService.getUserByLogin(user.getLogin());
        }
        catch (ResourceNotFoundException e){
            isPresent = false;
        }
        if (isPresent) {
            throw new ResourceConflictException("This login is already taken");
        }
        AppUserDTO newUser = appUserService.createUser(user);
        return newUser;
    }

    /**
     * wyszukuje co loginie w tokenie
     * @param token {@link JwtTokenDTO} token sesji
     * @return {@link AppUserDTO} uzytkownik
     * @throws ResourceNotFoundException gdy nie ma uzytkownika o podanym loginie
     */
    @Transactional
    public AppUserDTO getUserByLogin(JwtTokenDTO token) throws ResourceNotFoundException {
        String username = jwtUtil.extractUsernameFromToken(token.getToken());
        try{
            AppUserDTO existingUser = appUserService.getUserByLogin(username);
            return existingUser;
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
