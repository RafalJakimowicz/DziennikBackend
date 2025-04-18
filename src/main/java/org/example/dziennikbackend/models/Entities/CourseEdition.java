package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;

@Entity
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

    public CourseEdition() {}
    public CourseEdition(Course course, Semester semester, AppUser user) {
        this.course = course;
        this.semester = semester;
        this.user = user;
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
