package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter @Setter
@Entity
@Table(name = "semesters")
public class Semester {
    @Id
    @GeneratedValue
    private Long id;
    private String code;

    @Column(nullable = false)
    private LocalDateTime start_date;

    @Column(nullable = false)
    private LocalDateTime end_date;

    @OneToMany(
            mappedBy = "semester",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = LAZY
    )
    @JsonIgnore
    private List<CourseEdition> courseEditions = new ArrayList<>();

    public Semester() {}
    public Semester(String code, LocalDateTime start_date, LocalDateTime end_date, Major major) {
        this.code = code;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public void removeCourseEdition(CourseEdition edition){
        this.courseEditions.add(edition);
        edition.setSemester(this);
    }

    public void addCourseEdition(CourseEdition edition){
        this.courseEditions.add(edition);
        edition.setSemester(this);
    }
}
