package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;
import org.example.dziennikbackend.models.Enums.StudentStatus;

import java.util.List;

@Entity
@Table(name = "students")
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

    @OneToMany(mappedBy = "student")
    private List<StudentInGroup> studentInGroups;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances;

    public Student() {}
    public Student(String name, String surname, Integer album_number) {
        this.name = name;
        this.surname = surname;
        this.album_number = album_number;
    }

    public void removeAttendance(Attendance attendance){
        this.attendances.remove(attendance);
        attendance.setStudent(null);
    }

    public void addAttendance(Attendance attendance){
        this.attendances.add(attendance);
        attendance.setStudent(this);
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Attendance> getAttendances() {
        return this.attendances;
    }

    public void removeGrade(Grade grade){
        this.grades.remove(grade);
        grade.setStudent(this);
    }

    public void addGrade(Grade grade){
        this.grades.add(grade);
        grade.setStudent(this);
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Grade> getGrades() {
        return this.grades;
    }

    public void removeStudentInGroup(StudentInGroup sg){
        this.studentInGroups.remove(sg);
        sg.setStudent(null);
    }

    public void addStudentInGroup(StudentInGroup sg){
        this.studentInGroups.add(sg);
        sg.setStudent(this);
    }

    public List<StudentInGroup> getStudentInGroups() {
        return this.studentInGroups;
    }

    public void setStudentInGroups(List<StudentInGroup> studentInGroups) {
        this.studentInGroups = studentInGroups;
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
