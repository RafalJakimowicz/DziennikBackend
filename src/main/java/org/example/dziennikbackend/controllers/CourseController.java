package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.CourseDTO;
import org.example.dziennikbackend.models.Entities.Course;
import org.example.dziennikbackend.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO course = courseService.findById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course) {
        CourseDTO newCourse = courseService.createCourse(course);
        if (newCourse == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO course) {
        CourseDTO updatedCourse = courseService.updateCourse(id, course);
        if (updatedCourse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }
}
