package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.dziennikbackend.models.Enums.StudentStatus;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter @Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true, nullable = false, name = "index")
    private Integer index;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    @JsonIgnore
    private List<StudentInGroup> studentInGroups = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    @JsonIgnore
    private List<Grade> grades = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    @JsonIgnore
    private List<Attendance> attendances = new ArrayList<>();

    public Student() {}

    public Student(String name, String surname, Integer index) {
        this.name = name;
        this.surname = surname;
        this.index = index;
    }

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
        attendance.setStudent(this);
    }

    public void removeAttendance(Attendance attendance) {
        attendances.remove(attendance);
        attendance.setStudent(null);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
        grade.setStudent(this);
    }

    public void removeGrade(Grade grade) {
        grades.remove(grade);
        grade.setStudent(null);
    }

    public void addStudentInGroup(StudentInGroup sg) {
        studentInGroups.add(sg);
        sg.setStudent(this);
    }

    public void removeStudentInGroup(StudentInGroup sg) {
        studentInGroups.remove(sg);
        sg.setStudent(null);
    }
}
