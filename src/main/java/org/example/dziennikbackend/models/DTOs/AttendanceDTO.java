package org.example.dziennikbackend.models.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.dziennikbackend.models.Enums.AttendanceStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class AttendanceDTO {
    private Long id;
    private Long lessonId;
    private Long studentId;
    private AttendanceStatus status;
    private Long userId;
    private LocalDateTime date;

    public AttendanceDTO() {}

    public AttendanceDTO(Long id,Long lessonId, Long studentId, AttendanceStatus status, Long userId, LocalDateTime date) {
        this.lessonId = lessonId;
        this.studentId = studentId;
        this.status = status;
        this.userId = userId;
        this.date = date;
        this.id = id;
    }
}
