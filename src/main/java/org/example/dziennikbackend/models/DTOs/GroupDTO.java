package org.example.dziennikbackend.models.DTOs;

public class GroupDTO {
    private Long coursePartId;
    private String code;
    private Long userId;

    public GroupDTO(Long coursePartId, String code, Long userId) {
        this.coursePartId = coursePartId;
        this.code = code;
        this.userId = userId;
    }
    public GroupDTO() {}

    public Long getCoursePartId() {
        return this.coursePartId;
    }

    public void setCoursePartId(Long coursePartId) {
        this.coursePartId = coursePartId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
