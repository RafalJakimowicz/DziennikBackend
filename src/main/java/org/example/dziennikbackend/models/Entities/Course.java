package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique=true, nullable=false)
    private String code;
    private Long ects;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<CourseEdition> courseEditions = new ArrayList<>();

    public Course() {}
    public Course(String name, String code, Long ects){
        this.name = name;
        this.code = code;
        this.ects = ects;
    }

    public void AddCourseEdition(CourseEdition courseEdition) {
        this.courseEditions.add(courseEdition);
        courseEdition.setCourse(this);
    }

    public void RemoveCourseEdition(CourseEdition courseEdition) {
        this.courseEditions.remove(courseEdition);
        courseEdition.setCourse(null);
    }
}
