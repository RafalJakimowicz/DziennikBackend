package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;
import org.example.dziennikbackend.models.Enums.ClassesTypes;

@Entity
public class CoursePart {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_edition_id", nullable = false)
    private CourseEdition edition;

    private ClassesTypes type;

    private String description;

    private Short ects;

    public CoursePart() {}
    public CoursePart(CourseEdition edition, ClassesTypes type, String description, Short ects){
        this.edition = edition;
        this.type = type;
        this.description = description;
        this.ects = ects;
    }

    public Long getId() {
        return this.id;
    }

    public CourseEdition getEdition(){
        return this.edition;
    }

    public void setEdition(CourseEdition edition){
        this.edition = edition;
    }

    public ClassesTypes getType(){
        return this.type;
    }

    public void setType(ClassesTypes type){
        this.type = type;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Short getEcts(){
        return this.ects;
    }

    public void setEcts(Short ects){
        this.ects = ects;
    }
}
