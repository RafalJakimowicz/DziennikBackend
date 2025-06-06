package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.CourseEditionDTO;
import org.example.dziennikbackend.models.Entities.CourseEdition;
import org.example.dziennikbackend.services.CourseEditionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/course-editions")
public class CourseEditionController {
    private final CourseEditionService courseEditionService;
    public CourseEditionController(CourseEditionService courseEditionService) {
        this.courseEditionService = courseEditionService;
    }

    @GetMapping
    public ResponseEntity<List<CourseEditionDTO>> getAllCourseEditions() {
        return new ResponseEntity<>(courseEditionService.getAllCourseEditions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEditionDTO> getCourseEditionById(@PathVariable Long id) {
        CourseEditionDTO courseEdition = courseEditionService.getCourseEditionById(id);
        if (courseEdition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseEdition, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseEditionDTO> createCourseEdition(@RequestBody CourseEditionDTO courseEdition) {
        return new ResponseEntity<>(courseEditionService.createCourseEdition(courseEdition), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseEditionDTO> updateCourseEdition(@PathVariable Long id, @RequestBody CourseEditionDTO courseEdition) {
        CourseEditionDTO courseEditionUpdated = courseEditionService.updateCourseEdition(id, courseEdition);
        if (courseEditionUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseEditionUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseEdition> deleteCourseEdition(@PathVariable Long id) {
        courseEditionService.deleteCourseEdition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
