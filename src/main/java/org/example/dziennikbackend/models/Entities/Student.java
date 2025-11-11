package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    public Student() {}

    public Student(String name, String surname, Integer index) {
        this.name = name;
        this.surname = surname;
        this.index = index;
    }
}
