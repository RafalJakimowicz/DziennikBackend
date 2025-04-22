package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.Entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    Optional<Major> findByShortName(String shortName);
    Optional<Major> findByName(String name);
    boolean existsByShortName(String shortName);
}
