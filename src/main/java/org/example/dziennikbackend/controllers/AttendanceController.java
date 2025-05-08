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
    public ResponseEntity<AttendanceDTO> createAttendance(@RequestBody AttendanceDTO attendance) {
        return ResponseEntity.ok(attendanceService.createAttendance(attendance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable Long id, @RequestBody AttendanceDTO attendance) {
        AttendanceDTO upadatedAttendance = attendanceService.updateAttendance(id, attendance);
        if (upadatedAttendance != null) {
            return ResponseEntity.ok(upadatedAttendance);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AttendanceDTO> updateAttendanceStatus(@PathVariable Long id, @RequestParam AttendanceStatusDTO status) {
        AttendanceDTO updated = attendanceService.updateAttendanceStatus(id, status.getAttendanceStatus());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AttendanceDTO> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
