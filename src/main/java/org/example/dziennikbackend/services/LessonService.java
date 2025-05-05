package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.LessonDTO;
import org.example.dziennikbackend.models.Entities.Attendance;
import org.example.dziennikbackend.models.Entities.Lesson;
import org.example.dziennikbackend.repositories.AttendanceRepository;
import org.example.dziennikbackend.repositories.GroupRepository;
import org.example.dziennikbackend.repositories.LessonRepository;
import org.springframework.stereotype.Service;

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

    @Transactional
    public Lesson createLesson(LessonDTO lesson) {
        Lesson newLesson = new Lesson();
        newLesson.setGroup(groupRepository.getReferenceById(lesson.getGroupId()));
        newLesson.setStart(lesson.getStartTime());
        newLesson.setEnd(lesson.getEndTime());
        newLesson.setRoom(lesson.getRoom());
        newLesson.setSubject(lesson.getSubject());
        return lessonRepository.save(newLesson);
    }

    @Transactional
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Transactional
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteLessonById(Long id) {
        lessonRepository.deleteById(id);
    }

    @Transactional
    public Lesson updateLesson(Long id, LessonDTO lessonDTO) {
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
                lesson.setStart(lessonDTO.getStartTime());
            }
            if(lessonDTO.getEndTime() != null) {
                lesson.setEnd(lessonDTO.getEndTime());
            }
            return lessonRepository.save(lesson);
        }
        else {
            return null;
        }
    }

    @Transactional
    public List<Attendance> getLessonAttendances(Long lessonId) {
        return attendanceRepository.getAttendancesByLesson(lessonId);
    }
}
