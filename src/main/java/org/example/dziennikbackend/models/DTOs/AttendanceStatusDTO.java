package org.example.dziennikbackend.models.DTOs;

import org.example.dziennikbackend.models.Enums.AttendanceStatus;

public class AttendanceStatusDTO {
    private AttendanceStatus status;
    public AttendanceStatusDTO() {}
    public AttendanceStatusDTO(AttendanceStatus status) {
        this.status = status;
    }
    public AttendanceStatus getAttendanceStatus() {
        return this.status;
    }
    public void setAttendanceStatus(AttendanceStatus status) {
        this.status = status;
    }
}
