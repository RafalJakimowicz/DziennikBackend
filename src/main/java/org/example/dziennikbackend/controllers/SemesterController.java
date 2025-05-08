package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.SemesterDTO;
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
    public ResponseEntity<List<SemesterDTO>> getAllSemesters() {
        List<SemesterDTO> semesters = semesterService.getAllSemesters();
        if (semesters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(semesters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemesterDTO> getSemesterById(@PathVariable Long id) {
        SemesterDTO semester = semesterService.getSemesterById(id);
        if (semester == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(semester);
    }

    @PostMapping
    public ResponseEntity<SemesterDTO> createSemester(@RequestBody SemesterDTO semester) {
        SemesterDTO createdSemester = semesterService.createSemester(semester);
        if (createdSemester == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdSemester);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemesterDTO> updateSemester(@PathVariable Long id, @RequestBody SemesterDTO semester) {
        SemesterDTO returnedSemester = semesterService.updateSemester(id, semester);
        if (returnedSemester == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(returnedSemester);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemester(@PathVariable Long id) {
        semesterService.deleteSemesterById(id);
        return ResponseEntity.noContent().build();
    }
}
