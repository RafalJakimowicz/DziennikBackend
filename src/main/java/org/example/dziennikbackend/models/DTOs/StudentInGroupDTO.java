package org.example.dziennikbackend.models.DTOs;

public class StudentInGroupDTO {
    private Long studentId;
    private Long groupId;

    public StudentInGroupDTO(Long studentId, Long groupId) {
        this.studentId = studentId;
        this.groupId = groupId;
    }

    public StudentInGroupDTO() {}

    public Long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public Long getGroupId() {
        return this.groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
