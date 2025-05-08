package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Setter
@Table(name = "course_editions")
public class CourseEdition {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="semester_id", nullable=false)
    @JsonIgnore
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private AppUser user;

    @OneToMany(
            mappedBy = "edition",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<CoursePart> courseParts = new ArrayList<>();


    public CourseEdition() {}
    public CourseEdition(Course course, Semester semester, AppUser user) {
        this.course = course;
        this.semester = semester;
        this.user = user;
    }

    public void removeCoursePart(CoursePart part){
        this.courseParts.remove(part);
        part.setEdition(null);
    }

    public void addCoursePart(CoursePart part){
        this.courseParts.add(part);
        part.setEdition(this);
    }
}
