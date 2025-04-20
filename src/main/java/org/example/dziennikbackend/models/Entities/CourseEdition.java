package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course_editions")
public class CourseEdition {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name="semester_id", nullable=false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
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

    public void setCourseParts(List<CoursePart> courseParts) {
        this.courseParts = courseParts;
    }

    public List<CoursePart> getCourseParts() {
        return this.courseParts;
    }

    public Long getId() {
        return this.id;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Semester getSemester() {
        return this.semester;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public AppUser getUser() {
        return this.user;
    }
}
