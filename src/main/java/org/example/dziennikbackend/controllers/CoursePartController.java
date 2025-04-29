package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.CoursePartDTO;
import org.example.dziennikbackend.models.Entities.CoursePart;
import org.example.dziennikbackend.services.CoursePartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/course-parts")
public class CoursePartController {
    private final CoursePartService coursePartService;
    public CoursePartController(CoursePartService coursePartService) {
        this.coursePartService = coursePartService;
    }

    @GetMapping
    public ResponseEntity<List<CoursePart>> getAllCourseParts() {
        List<CoursePart> courseParts = coursePartService.findAll();
        if (courseParts == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseParts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursePart> getCoursePartById(@PathVariable Long id) {
        CoursePart coursePart = coursePartService.findById(id);
        if (coursePart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coursePart);
    }

    @PostMapping
    public ResponseEntity<CoursePart> createCoursePart(@RequestBody CoursePartDTO coursePart) {
        CoursePart newPart = coursePartService.create(coursePart);
        if (newPart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newPart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoursePart> updateCoursePart(@PathVariable Long id, @RequestBody CoursePartDTO coursePart) {
        CoursePart updated = coursePartService.updateCoursePart(id, coursePart);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoursePart(@PathVariable Long id) {
        coursePartService.deleteCoursePart(id);
        return ResponseEntity.noContent().build();
    }
}
