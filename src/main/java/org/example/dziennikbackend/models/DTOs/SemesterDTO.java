package org.example.dziennikbackend.models.DTOs;

import java.time.LocalDateTime;

public class SemesterDTO {
    private String code;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long majorId;

    public SemesterDTO(String code, LocalDateTime startDate, LocalDateTime endDate, Long majorId) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.majorId = majorId;
    }

    public SemesterDTO() {}

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getMajorId() {
        return this.majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }
}
