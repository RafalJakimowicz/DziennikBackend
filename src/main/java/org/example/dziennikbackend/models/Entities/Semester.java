package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Semester {
    @Id
    @GeneratedValue
    private Long id;
    private String code;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    public Semester() {}
    public Semester(String code, LocalDateTime start, LocalDateTime end, Major major) {
        this.code = code;
        this.start = start;
        this.end = end;
        this.major = major;
    }

    public Long getId() {
        return this.id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Major getMajor() {
        return this.major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
