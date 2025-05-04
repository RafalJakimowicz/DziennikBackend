package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.LessonDTO;
import org.example.dziennikbackend.models.Entities.Lesson;
import org.example.dziennikbackend.repositories.AttendanceRepository;
import org.example.dziennikbackend.repositories.GroupRepository;
import org.example.dziennikbackend.repositories.LessonRepository;
import org.springframework.stereotype.Service;

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
}
