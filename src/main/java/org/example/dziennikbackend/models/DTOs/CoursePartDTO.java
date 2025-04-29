package org.example.dziennikbackend.models.DTOs;

import org.example.dziennikbackend.models.Enums.ClassesTypes;

public class CoursePartDTO {
    private Long courseEditionId;
    private ClassesTypes type;
    private String description;
    private Short ects;
    
    public CoursePartDTO() {}
    public CoursePartDTO(Long courseEditionId, ClassesTypes type, String description, Short ects) {
        this.courseEditionId = courseEditionId;
        this.type = type;
        this.description = description;
        this.ects = ects;
    }
    
    public Long getCourseEditionId() {
        return this.courseEditionId;
    }
    
    public void setCourseEditionId(Long courseEditionId) {
        this.courseEditionId = courseEditionId;
    }
    
    public ClassesTypes getType() {
        return this.type;
    }
    
    public void setType(ClassesTypes type) {
        this.type = type;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Short getEts() {
        return this.ects;
    }
    
    public void setEts(Short ects) {
        this.ects = ects;
    }
}
