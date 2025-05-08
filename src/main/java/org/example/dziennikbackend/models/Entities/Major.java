package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Getter @Setter
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
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    public Major() {}
    public Major(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
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
