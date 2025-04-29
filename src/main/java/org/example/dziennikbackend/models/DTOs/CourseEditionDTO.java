package org.example.dziennikbackend.models.DTOs;

public class CourseEditionDTO {
    private Long courseId;
    private Long semesterId;
    private Long userId;

    public CourseEditionDTO(Long courseId, Long semesterId, Long userId) {
        this.courseId = courseId;
        this.semesterId = semesterId;
        this.userId = userId;
    }

    public CourseEditionDTO() {}

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSemesterId() {
        return this.semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
