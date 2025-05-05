package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.AttendanceDTO;
import org.example.dziennikbackend.models.Entities.Attendance;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.example.dziennikbackend.repositories.AttendanceRepository;
import org.example.dziennikbackend.repositories.LessonRepository;
import org.example.dziennikbackend.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final AppUserRepository appUserRepository;
    public AttendanceService(AttendanceRepository attendanceRepository, LessonRepository lessonRepository, StudentRepository studentRepository, AppUserRepository appUserRepository) {
        this.attendanceRepository = attendanceRepository;
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    public Attendance createAttendance(AttendanceDTO attendanceDTO) {
        Attendance attendance = new Attendance();
        attendance.setLesson(lessonRepository.getReferenceById(attendanceDTO.getLessonId()));
        attendance.setStudent(studentRepository.getReferenceById(attendanceDTO.getStudentId()));
        attendance.setUser(appUserRepository.getReferenceById(attendanceDTO.getUserId()));
        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setDate(attendanceDTO.getDate());
        return attendanceRepository.save(attendance);
    }

    @Transactional
    public Attendance updateAttendance(Long id, AttendanceDTO attendanceDTO) {
        Attendance toUpdate = attendanceRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            if (attendanceDTO.getLessonId() != null) {
                toUpdate.setLesson(lessonRepository.getReferenceById(attendanceDTO.getLessonId()));
            }
            if (attendanceDTO.getStudentId() != null) {
                toUpdate.setStudent(studentRepository.getReferenceById(attendanceDTO.getStudentId()));
            }
            if (attendanceDTO.getUserId() != null) {
                toUpdate.setUser(appUserRepository.getReferenceById(attendanceDTO.getUserId()));
            }
            if (attendanceDTO.getStatus() != null) {
                toUpdate.setStatus(attendanceDTO.getStatus());
            }
            if (attendanceDTO.getDate() != null) {
                toUpdate.setDate(attendanceDTO.getDate());
            }
            return attendanceRepository.save(toUpdate);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}
