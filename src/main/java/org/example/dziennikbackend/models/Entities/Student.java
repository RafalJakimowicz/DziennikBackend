package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.dziennikbackend.models.Enums.StudentStatus;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "students")
public class Student {

    /* ──────────────────── pola podstawowe ──────────────────── */
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true, nullable = false, name = "album_number")
    private Integer albumNumber;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "major_id")
    @JsonIgnore                     // nie serializujemy Major w JSON‑ie
    private Major major;

    @Column(nullable = false)
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentStatus studentStatus;

    /* ──────────────────── relacje kolekcyjne ──────────────────── */
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    @JsonIgnore
    private List<StudentInGroup> studentInGroups = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    @JsonIgnore
    private List<Grade> grades = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    @JsonIgnore
    private List<Attendance> attendances = new ArrayList<>();

    /* ──────────────────── konstruktory ──────────────────── */
    public Student() {}

    public Student(String name, String surname, Integer albumNumber) {
        this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
    }

    /* ──────────────────── metody pomocnicze relacji ──────────────────── */
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

    /* ──────────────────── gettery / settery ──────────────────── */
    public Long getId() {return id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public Integer getAlbumNumber() {return albumNumber;}

    public void setAlbumNumber(Integer albumNumber) {this.albumNumber = albumNumber;}

    public Major getMajor() {return major;}

    public void setMajor(Major major) {this.major = major;}

    public Integer getYear() {return year;}

    public void setYear(Integer year) {this.year = year;}

    public StudentStatus getStudentStatus() {return studentStatus;}

    public void setStudentStatus(StudentStatus studentStatus) {this.studentStatus = studentStatus;}

    public List<StudentInGroup> getStudentInGroups() {return studentInGroups;}

    public void setStudentInGroups(List<StudentInGroup> studentInGroups) {this.studentInGroups = studentInGroups;}

    public List<Grade> getGrades() {return grades;}

    public void setGrades(List<Grade> grades) {this.grades = grades;}

    public List<Attendance> getAttendances() {return attendances;}

    public void setAttendances(List<Attendance> attendances) {this.attendances = attendances;}
}
