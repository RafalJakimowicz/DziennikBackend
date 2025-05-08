package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.dziennikbackend.models.Enums.AttendanceStatus;
import org.example.dziennikbackend.models.Enums.StudentStatus;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter @Setter
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
}
