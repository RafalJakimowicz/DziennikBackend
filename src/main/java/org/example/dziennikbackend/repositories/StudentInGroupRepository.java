package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.Entities.StudentInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentInGroupRepository extends JpaRepository<StudentInGroup, Long> {
    List<StudentInGroup> findAllByStudentId(Long studentId);
    List<StudentInGroup> findAllByGroupId(Long groupId);
}
