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
    Optional<Student> findByAlbumNumber(Integer albumNumber);

    @Modifying
    @Transactional
    @Query("UPDATE Student AS s SET s.studentStatus = :studentStatus WHERE s.albumNumber = :albumNumber")
    int updateStudentStatus(@Param("albumNumber") Integer albumNumber, @Param("studentStatus") StudentStatus studentStatus);

    List<Student> findByMajor(Major major);
}
