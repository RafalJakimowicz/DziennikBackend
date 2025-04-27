package org.example.dziennikbackend.controllers;

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
    public ResponseEntity<List<Major>> getAllMajors() {
        List<Major> majors = majorService.getAllMajors();
        if (majors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(majors);
    }

    @GetMapping("/{shortName}")
    public ResponseEntity<Major> getMajorById(@PathVariable String shortName) {
        Major major = majorService.getMajorByShortName(shortName);
        if (major == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(major);
    }

    @PostMapping
    public ResponseEntity<Major> createMajor(@RequestBody Major major) {
        Major newMajor = majorService.createMajor(major);
        if (newMajor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newMajor);
    }

    @PutMapping("/{shortName}")
    public ResponseEntity<Major> updateMajor(@PathVariable String shortName, @RequestBody Major major) {
        Major updatedMajor = majorService.updateMajor(shortName, major);
        if (updatedMajor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMajor);
    }

    @DeleteMapping("/{shortName}")
    public ResponseEntity<Major> deleteMajor(@PathVariable String shortName) {
        majorService.deleteMajor(shortName);
        return ResponseEntity.noContent().build();
    }
}
