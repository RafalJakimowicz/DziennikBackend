package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.AttendanceDTO;
import org.example.dziennikbackend.models.DTOs.AttendanceStatusDTO;
import org.example.dziennikbackend.models.Entities.Attendance;
import org.example.dziennikbackend.services.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/attendances")
public class AttendanceController {
    private final AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody AttendanceDTO attendance) {
        return ResponseEntity.ok(attendanceService.createAttendance(attendance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody AttendanceDTO attendance) {
        Attendance upadatedAttendance = attendanceService.updateAttendance(id, attendance);
        if (upadatedAttendance != null) {
            return ResponseEntity.ok(upadatedAttendance);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Attendance> updateAttendanceStatus(@PathVariable Long id, @RequestParam AttendanceStatusDTO status) {
        Attendance updated = attendanceService.updateAttendanceStatus(id, status.getAttendanceStatus());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Attendance> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
