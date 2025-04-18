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

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @Column(nullable = false)
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'AKTYWNY'")
    private StudentStatus studentStatus;

    public Student() {}
    public Student(String name, String surname, Integer album_number) {
        this.name = name;
        this.surname = surname;
        this.album_number = album_number;
    }

    public Major getMajor(){
        return this.major;
    }

    public void setMajor(Major major){
        this.major = major;
    }

    public Integer getYear(){
        return this.year;
    }

    public void setYear(Integer year){
        this.year = year;
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
