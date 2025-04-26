package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.Entities.Semester;
import org.example.dziennikbackend.services.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{code}")
    public ResponseEntity<Semester> getSemesterByCode(@PathVariable String code) {
        Semester semester = semesterService.getSemesterByCode(code);
        if (semester == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(semester);
    }

    @PostMapping
    public ResponseEntity<Semester> createSemester(@RequestBody Semester semester) {
        Semester createdSemester = semesterService.createSemester(semester);
        if (createdSemester == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdSemester);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Semester> updateSemester(@PathVariable String code, @RequestBody Semester semester) {
        Semester returnedSemester = semesterService.updateSemester(semester);
        if (returnedSemester == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(returnedSemester);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity deleteSemester(@PathVariable String code) {
        semesterService.deleteSemesterByCode(code);
        return ResponseEntity.noContent().build();
    }
}
