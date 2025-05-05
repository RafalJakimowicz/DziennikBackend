package org.example.dziennikbackend.models.DTOs;

import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.models.Enums.StudentStatus;

public class StudentDTO {
    private String name;
    private String surname;
    private Integer albumNumber;
    private Long majorId;
    private Integer year;
    private StudentStatus status;

    public StudentDTO() {}

    public StudentDTO(String name, String surname, Integer albumNumber, Long majorId, Integer year, StudentStatus status) {
        this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
        this.majorId = majorId;
        this.year = year;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAlbumNumber() {
        return this.albumNumber;
    }

    public void setAlbumNumber(Integer albumNumber) {
        this.albumNumber = albumNumber;
    }

    public Long getMajorId() {
        return this.majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public StudentStatus getStatus() {
        return this.status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }
}
