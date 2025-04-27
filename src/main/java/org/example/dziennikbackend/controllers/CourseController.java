package org.example.dziennikbackend.controllers;

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

    @GetMapping("/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String code) {
        Course course = courseService.findByCode(code);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course newCourse = courseService.createCourse(course);
        if (newCourse == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newCourse);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Course> updateCourse(@PathVariable String code, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(code, course);
        if (updatedCourse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String code) {
        courseService.deleteCourseByCode(code);
        return ResponseEntity.noContent().build();
    }
}
