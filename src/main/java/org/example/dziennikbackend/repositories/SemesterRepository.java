package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.Entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    boolean existsSemestersByCode(String code);
    Optional<Semester> findSemestersByCode(String code);
}
