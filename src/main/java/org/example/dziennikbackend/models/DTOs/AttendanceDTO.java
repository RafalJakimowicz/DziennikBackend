package org.example.dziennikbackend.models.DTOs;

import org.example.dziennikbackend.models.Enums.AttendanceStatus;

import java.time.LocalDateTime;

public class AttendanceDTO {
    private Long lessonId;
    private Long studentId;
    private AttendanceStatus status;
    private Long userId;
    private LocalDateTime date;

    public AttendanceDTO() {}

    public AttendanceDTO(Long lessonId, Long studentId, AttendanceStatus status, Long userId, LocalDateTime date) {
        this.lessonId = lessonId;
        this.studentId = studentId;
        this.status = status;
        this.userId = userId;
        this.date = date;
    }

    public Long getLessonId() {
        return this.lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public AttendanceStatus getStatus() {
        return this.status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
