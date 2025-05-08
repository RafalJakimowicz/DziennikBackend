package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name="lessons")
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    @JsonIgnore
    private Group group;

    @Column(nullable = false)
    private LocalDateTime start_date;

    @Column(nullable = false)
    private LocalDateTime end_date;

    private String subject;

    private String room;

    @OneToMany(
            mappedBy = "lesson",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Attendance> attendances = new ArrayList<>();

    public Lesson() {}
    public Lesson(Group group, LocalDateTime start_date, LocalDateTime end_date, String subject, String room){
        this.group = group;
        this.start_date = start_date;
        this.end_date = end_date;
        this.subject = subject;
        this.room = room;
    }

    public void removeAttendance(Attendance attendance){
        this.attendances.remove(attendance);
        attendance.setLesson(null);
    }

    public void addAttendance(Attendance attendance){
        this.attendances.add(attendance);
        attendance.setLesson(this);
    }
}
