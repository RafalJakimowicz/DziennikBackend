package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique=true, nullable=false)
    private String code;
    private Short ects;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseEdition> courseEditions;

    public Course() {}
    public Course(String name, String code, Short ects){
        this.name = name;
        this.code = code;
        this.ects = ects;
    }

    public void setCourseEditions(List<CourseEdition> courseEditions) {
        this.courseEditions = courseEditions;
    }

    public List<CourseEdition> getCourseEditions() {
        return courseEditions;
    }

    public void AddCourseEdition(CourseEdition courseEdition) {
        this.courseEditions.add(courseEdition);
        courseEdition.setCourse(this);
    }

    public void RemoveCourseEdition(CourseEdition courseEdition) {
        this.courseEditions.remove(courseEdition);
        courseEdition.setCourse(null);
    }

    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public void setEts(Short ects){
        this.ects = ects;
    }

    public Short getEts(){
        return this.ects;
    }
}
