package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.AttendanceDTO;
import org.example.dziennikbackend.models.DTOs.AttendanceStatusDTO;
import org.example.dziennikbackend.models.Entities.Attendance;
import org.example.dziennikbackend.services.AttendanceService;
import org.example.dziennikbackend.utils.ErrorMessage;
import org.example.dziennikbackend.utils.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/attendances")
public class AttendanceController {
    private final AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * Creates new attendance
     * @param attendance {@link AttendanceDTO}
     * @return OK status after creation
     */
    @PostMapping
    public ResponseEntity<AttendanceDTO> createAttendance(@RequestBody AttendanceDTO attendance) {
        return ResponseEntity.ok(attendanceService.createAttendance(attendance));
    }

    /**
     * Updates attendance status
     * @param id Attendance id
     * @param status status to be changed to
     * @return {@link AttendanceDTO} if successful otherwise returns {@link ErrorMessage} with error data
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateAttendanceStatus(@PathVariable Long id, @RequestParam AttendanceStatusDTO status) {
        AttendanceDTO updated;
        try {
            updated = attendanceService.updateAttendanceStatus(id, status.getAttendanceStatus());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorMessage().getObject("Updating attendance status", e.toString(), 400)
            );
        }
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
