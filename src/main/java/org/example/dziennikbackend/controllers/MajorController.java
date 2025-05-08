package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.MajorDTO;
import org.example.dziennikbackend.models.Entities.Major;
import org.example.dziennikbackend.services.MajorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/majors")
public class MajorController {
    private final MajorService majorService;
    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping
    public ResponseEntity<List<MajorDTO>> getAllMajors() {
        List<MajorDTO> majors = majorService.getAllMajors();
        if (majors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(majors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MajorDTO> getMajorById(@PathVariable Long id) {
        MajorDTO major = majorService.getMajorById(id);
        if (major == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(major);
    }

    @PostMapping
    public ResponseEntity<MajorDTO> createMajor(@RequestBody MajorDTO major) {
        MajorDTO newMajor = majorService.createMajor(major);
        if (newMajor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newMajor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MajorDTO> updateMajor(@PathVariable Long id, @RequestBody MajorDTO major) {
        MajorDTO updatedMajor = majorService.updateMajor(id, major);
        if (updatedMajor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMajor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMajor(@PathVariable Long id) {
        majorService.deleteMajor(id);
        return ResponseEntity.noContent().build();
    }
}
