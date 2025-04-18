package org.example.dziennikbackend.repositories;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.models.Enums.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByAlbum_number(Integer album_number);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.studentStatus = :studentStatus WHERE s.album_number = :album_number")
    int updateStudentStatus(@Param("album_number") Integer album_number, @Param("studentStatus") StudentStatus studentStatus);
}
