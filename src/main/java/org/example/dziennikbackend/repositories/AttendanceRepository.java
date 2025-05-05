package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.Entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query(value = "SELECT a.* FROM attendances AS a JOIN lessons AS l ON a.lesson_id = l.id WHERE l.id = :lesson_id", nativeQuery = true)
    List<Attendance> getAttendancesByLesson(@Param("lessonId") Long lessonsId);
}
