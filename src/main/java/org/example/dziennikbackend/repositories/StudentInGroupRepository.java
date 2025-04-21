package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.Entities.StudentInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInGroupRepository extends JpaRepository<StudentInGroup, Long> {
}
