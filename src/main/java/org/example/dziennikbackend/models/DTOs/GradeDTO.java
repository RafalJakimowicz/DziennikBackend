package org.example.dziennikbackend.models.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GradeDTO {
    private Long studentId;
    private BigDecimal score;
    private String comment;
    private Long userId;
    private LocalDateTime date;
    private Long groupId;
    
    public GradeDTO(Long studentId, BigDecimal score, String comment, Long userId, LocalDateTime date, Long groupId) {
        this.studentId = studentId;
        this.score = score;
        this.comment = comment;
        this.userId = userId;
        this.date = date;
        this.groupId = groupId;
    }
    
    public GradeDTO() {}
    
    public Long getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public BigDecimal getScore() {
        return this.score;
    }
    
    public void setScore(BigDecimal score) {
        this.score = score;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
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
    
    public Long getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
