package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;
import org.example.dziennikbackend.models.Enums.AttendanceStatus;
import org.example.dziennikbackend.models.Enums.StudentStatus;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name="attendances")
public class Attendance {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @ColumnDefault("'NIEOBECNY'")
    private AttendanceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private AppUser user;

    @Column(nullable = false)
    private LocalDateTime date;

    public Attendance(){}
    public Attendance(Lesson lesson, Student student, AttendanceStatus status, AppUser user, LocalDateTime date){
        this.lesson = lesson;
        this.student = student;
        this.status = status;
        this.user = user;
        this.date = date;
    }

    public Long getId(){
        return this.id;
    }

    public Lesson getLesson(){
        return this.lesson;
    }

    public void setLesson(Lesson lesson){
        this.lesson = lesson;
    }

    public Student getStudent(){
        return this.student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public AttendanceStatus getStatus(){
        return this.status;
    }

    public void setStatus(AttendanceStatus status){
        this.status = status;
    }

    public AppUser getUser(){
        return this.user;
    }

    public void setUser(AppUser user){
        this.user = user;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public void setDate(LocalDateTime date){
        this.date = date;
    }
}
