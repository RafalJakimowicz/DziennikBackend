package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.AttendanceDTO;
import org.example.dziennikbackend.models.Entities.Attendance;
import org.example.dziennikbackend.models.Enums.AttendanceStatus;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.example.dziennikbackend.repositories.AttendanceRepository;
import org.example.dziennikbackend.repositories.LessonRepository;
import org.example.dziennikbackend.repositories.StudentRepository;
import org.example.dziennikbackend.utils.DTOMapper;
import org.example.dziennikbackend.utils.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Transactional
    public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceRepository.save(DTOMapper.map(attendanceDTO, Attendance.class));
        return DTOMapper.map(attendance, AttendanceDTO.class);
    }

    @Transactional
    public AttendanceDTO updateAttendanceStatus(Long id, AttendanceStatus status) throws ResourceNotFoundException{
        Attendance toUpdate = attendanceRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            if (status != null) {
                toUpdate.setStatus(status);
            }
            return DTOMapper.map(attendanceRepository.save(toUpdate), AttendanceDTO.class);
        }
        throw new ResourceNotFoundException("Attendance with id " + id + " not found");
    }

    @Transactional
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}
