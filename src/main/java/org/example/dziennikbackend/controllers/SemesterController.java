package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.Entities.Semester;
import org.example.dziennikbackend.services.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/private/semesters")
public class SemesterController {
    private final SemesterService semesterService;
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping
    public ResponseEntity<List<Semester>> getAllSemesters() {
        List<Semester> semesters = semesterService.getAllSemesters();
        if (semesters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(semesters);
    }
}
