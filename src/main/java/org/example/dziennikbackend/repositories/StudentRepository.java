package org.example.dziennikbackend.repositories;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.Entities.Major;
import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.models.Enums.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT s.* FROM students AS s JOIN students_in_groups AS sg ON s.id = sg.student_id WHERE sg.id = :groupId", nativeQuery = true)
    List<Student> findStudentsInGroup(@Param ("groupId") Long groupId);


}
