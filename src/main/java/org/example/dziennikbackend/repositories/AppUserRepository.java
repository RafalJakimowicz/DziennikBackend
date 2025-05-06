package org.example.dziennikbackend.repositories;

import aj.org.objectweb.asm.commons.Remapper;
import org.example.dziennikbackend.models.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByLogin(String Login);
    Optional<AppUser> findByEmail(String email);
}
