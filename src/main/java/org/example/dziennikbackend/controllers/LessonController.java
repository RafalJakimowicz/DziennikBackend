package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.AttendanceDTO;
import org.example.dziennikbackend.models.DTOs.LessonDTO;
import org.example.dziennikbackend.services.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/lessons")
public class LessonController {
    private final LessonService lessonService;
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public ResponseEntity<List<LessonDTO>> getAllLessons() {
        List<LessonDTO> lessons = lessonService.getAllLessons();
        if (lessons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Long id) {
        LessonDTO lesson = lessonService.getLessonById(id);
        if (lesson == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lesson);
    }

    @PostMapping
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lesson) {
        return ResponseEntity.ok(lessonService.createLesson(lesson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDTO> updateLesson(@PathVariable Long id, @RequestBody LessonDTO lesson) {
        return ResponseEntity.ok(lessonService.updateLesson(id, lesson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/attendances")
    public ResponseEntity<List<AttendanceDTO>> getLessonAttendances(@PathVariable Long id) {
        List<AttendanceDTO> attendances = lessonService.getLessonAttendances(id);
        if (attendances.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attendances);
    }
}
