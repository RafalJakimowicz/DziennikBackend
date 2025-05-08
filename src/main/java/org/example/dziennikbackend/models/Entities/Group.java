package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_part_id")
    @JsonIgnore
    private CoursePart coursePart;

    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private AppUser user;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<StudentInGroup> studentInGroups = new ArrayList<>();

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Grade> grades = new ArrayList<>();

    public Group() {}
    public Group(CoursePart part, String code, AppUser user){
        this.coursePart = part;
        this.code = code;
        this.user = user;
    }

    public void removeGrade(Grade grade){
        this.grades.remove(grade);
        grade.setGroup(null);
    }

    public void addGrade(Grade grade){
        this.grades.add(grade);
        grade.setGroup(this);
    }

    public void removeStudentInGroup(StudentInGroup sg){
        this.studentInGroups.remove(sg);
        sg.setGroup(null);
    }

    public void addStudentInGroup(StudentInGroup sg){
        this.studentInGroups.add(sg);
        sg.setGroup(this);
    }

    public void removeLesson(Lesson lesson){
        this.lessons.remove(lesson);
        lesson.setGroup(null);
    }

    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
        lesson.setGroup(this);
    }
}
