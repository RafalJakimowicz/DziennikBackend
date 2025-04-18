package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;
import org.example.dziennikbackend.models.Enums.StudentStatus;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true, nullable = false)
    private Integer album_number;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'ACTIVE'")
    private StudentStatus studentStatus;

    public Student() {}
    public Student(String name, String surname, Integer album_number) {
        this.name = name;
        this.surname = surname;
        this.album_number = album_number;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getSurname(){
        return this.surname;
    }

    public void setAlbum_number(Integer album_number){
        this.album_number = album_number;
    }

    public Integer getAlbum_number(){
        return this.album_number;
    }

    public void setStatus(StudentStatus studentStatus){
        this.studentStatus = studentStatus;
    }

    public StudentStatus getStatus(){
        return this.studentStatus;
    }
}
