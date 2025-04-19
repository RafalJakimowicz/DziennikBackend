package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="lessons")
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    private String subject;

    private String room;

    @OneToMany(mappedBy = "lesson")
    private List<Attendance> attendances;

    public Lesson() {}
    public Lesson(Group group, LocalDateTime start, LocalDateTime end, String subject, String room){
        this.group = group;
        this.start = start;
        this.end = end;
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

    public List<Attendance> getAttendances() {
        return this.attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Long getId(){
        return this.id;
    }

    public Group getGroup(){
        return this.group;
    }

    public void setGroup(Group group){
        this.group = group;
    }

    public LocalDateTime getStart(){
        return this.start;
    }

    public void setStart(LocalDateTime start){
        this.start = start;
    }

    public LocalDateTime getEnd(){
        return this.end;
    }

    public void setEnd(LocalDateTime end){
        this.end = end;
    }

    public String getSubject(){
        return this.subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getRoom(){
        return this.room;
    }

    public void setRoom(String room){
        this.room = room;
    }
}
