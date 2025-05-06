package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "majors")
public class Major {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(unique = true)
    private String shortName;

    @OneToMany(mappedBy = "major", cascade = ALL, orphanRemoval = true, fetch = LAZY)
    @JsonManagedReference(value = "major‑students")
    private List<Student> students = new ArrayList<>();

    public Major() {}
    public Major(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public void setId(Long id) {
        this.id = id;
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
