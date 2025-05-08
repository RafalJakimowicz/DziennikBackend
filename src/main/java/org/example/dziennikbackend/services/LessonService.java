package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.AttendanceDTO;
import org.example.dziennikbackend.models.DTOs.LessonDTO;
import org.example.dziennikbackend.models.Entities.Attendance;
import org.example.dziennikbackend.models.Entities.Lesson;
import org.example.dziennikbackend.repositories.AttendanceRepository;
import org.example.dziennikbackend.repositories.GroupRepository;
import org.example.dziennikbackend.repositories.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final AttendanceRepository attendanceRepository;
    public LessonService(LessonRepository lessonRepository, GroupRepository groupRepository, AttendanceRepository attendanceRepository) {
        this.lessonRepository = lessonRepository;
        this.groupRepository = groupRepository;
        this.attendanceRepository = attendanceRepository;
    }

    private LessonDTO changeObjectToDTO(Lesson lesson) {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.getId());
        lessonDTO.setSubject(lesson.getSubject());
        lessonDTO.setRoom(lesson.getRoom());
        lessonDTO.setEndTime(lesson.getEnd_date());
        lessonDTO.setStartTime(lesson.getStart_date());
        lessonDTO.setGroupId(lesson.getGroup().getId());
        return lessonDTO;
    }

    private Lesson changeDTOToObject(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setId(lessonDTO.getId());
        lesson.setSubject(lessonDTO.getSubject());
        lesson.setRoom(lessonDTO.getRoom());
        lesson.setEnd_date(lessonDTO.getEndTime());
        lesson.setStart_date(lessonDTO.getStartTime());
        lesson.setGroup(groupRepository.getReferenceById(lessonDTO.getGroupId()));
        return lesson;
    }

    @Transactional
    public LessonDTO createLesson(LessonDTO lesson) {
        return changeObjectToDTO(lessonRepository.save(changeDTOToObject(lesson)));
    }

    @Transactional
    public List<LessonDTO> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        List<LessonDTO> lessonDTOs = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonDTOs.add(changeObjectToDTO(lesson));
        }
        return lessonDTOs;
    }

    @Transactional
    public LessonDTO getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if (lesson == null) {
            return null;
        } else {
            return changeObjectToDTO(lesson);
        }
    }

    @Transactional
    public void deleteLessonById(Long id) {
        lessonRepository.deleteById(id);
    }

    @Transactional
    public LessonDTO updateLesson(Long id, LessonDTO lessonDTO) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if (lesson != null) {
            if (lessonDTO.getRoom() != null) {
                lesson.setRoom(lessonDTO.getRoom());
            }
            if (lessonDTO.getSubject() != null) {
                lesson.setSubject(lessonDTO.getSubject());
            }
            if(lessonDTO.getGroupId() != null) {
                lesson.setGroup(groupRepository.getReferenceById(lessonDTO.getGroupId()));
            }
            if(lessonDTO.getStartTime() != null) {
                lesson.setStart_date(lessonDTO.getStartTime());
            }
            if(lessonDTO.getEndTime() != null) {
                lesson.setEnd_date(lessonDTO.getEndTime());
            }
            return changeObjectToDTO(lessonRepository.save(lesson));
        }
        else {
            return null;
        }
    }

    private AttendanceDTO changeObjectToDTO(Attendance attendance) {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(attendance.getId());
        attendanceDTO.setStudentId(attendance.getStudent().getId());
        attendanceDTO.setLessonId(attendance.getLesson().getId());
        attendanceDTO.setStatus(attendance.getStatus());
        attendanceDTO.setDate(attendance.getDate());
        attendanceDTO.setUserId(attendance.getUser().getId());
        return attendanceDTO;
    }

    @Transactional
    public List<AttendanceDTO> getLessonAttendances(Long lessonId) {
        List<Attendance> attendances = attendanceRepository.getAttendancesByLesson(lessonId);
        List<AttendanceDTO> attendanceDTOs = new ArrayList<>();
        for (Attendance attendance : attendances) {
            attendanceDTOs.add(changeObjectToDTO(attendance));
        }
        return attendanceDTOs;
    }
}
