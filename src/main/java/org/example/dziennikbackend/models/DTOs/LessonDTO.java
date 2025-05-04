package org.example.dziennikbackend.models.DTOs;

import java.time.LocalDateTime;

public class LessonDTO {
    private Long groupId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String subject;
    private String room;

    public LessonDTO(Long groupId, LocalDateTime startTime, LocalDateTime endTime, String subject, String room) {
        this.groupId = groupId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.room = room;
    }

    public LessonDTO() {}

    public Long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
