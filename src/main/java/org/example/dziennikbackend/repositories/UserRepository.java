package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByLogin(String Login);
    public Optional<UserEntity> findByEmail(String email);
}
