package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "majors")
public class Major {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(unique = true)
    private String shortName;

    @JsonIgnore
    @OneToMany(
            mappedBy = "major",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Student> students = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "major",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Semester> semesters = new ArrayList<>();

    public Major() {}
    public Major(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public List<Semester> getSemesters() {
        return this.semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public void addSemester(Semester semester) {
        this.semesters.add(semester);
        semester.setMajor(this);
    }

    public void removeSemester(Semester semester){
        this.semesters.remove(semester);
        semester.setMajor(null);
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        this.students.add(student);
        student.setMajor(this);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
        student.setMajor(null);
    }
}
