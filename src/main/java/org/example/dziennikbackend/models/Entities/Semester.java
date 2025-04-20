package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "semesters")
public class Semester {
    @Id
    @GeneratedValue
    private Long id;
    private String code;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @OneToMany(
            mappedBy = "semester",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<CourseEdition> courseEditions = new ArrayList<>();

    public Semester() {}
    public Semester(String code, LocalDateTime start, LocalDateTime end, Major major) {
        this.code = code;
        this.start = start;
        this.end = end;
        this.major = major;
    }

    public void removeCourseEdition(CourseEdition edition){
        this.courseEditions.add(edition);
        edition.setSemester(this);
    }

    public void addCourseEdition(CourseEdition edition){
        this.courseEditions.add(edition);
        edition.setSemester(this);
    }

    public void setCourseEditions(List<CourseEdition> courseEditions) {
        this.courseEditions = courseEditions;
    }

    public List<CourseEdition> getCourseEditions() {
        return this.courseEditions;
    }

    public Long getId() {
        return this.id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Major getMajor() {
        return this.major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
