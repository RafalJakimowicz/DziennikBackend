package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.GradeDTO;
import org.example.dziennikbackend.models.Entities.Grade;
import org.example.dziennikbackend.services.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/grades")
public class GradeController {
    private final GradeService gradeService;
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO grade) {
        return ResponseEntity.ok(gradeService.createGrade(grade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable Long id, @RequestBody GradeDTO grade) {
        GradeDTO updated = gradeService.updateGrade(id, grade);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
