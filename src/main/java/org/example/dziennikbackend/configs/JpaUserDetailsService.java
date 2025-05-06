package org.example.dziennikbackend.configs;

import lombok.RequiredArgsConstructor;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repo.findByLogin(username)
                .map(u -> User.withUsername(u.getLogin())
                        .password(u.getPassword())
                        .build())
                .orElseThrow(() ->
                        new UsernameNotFoundException(username));
    }
}