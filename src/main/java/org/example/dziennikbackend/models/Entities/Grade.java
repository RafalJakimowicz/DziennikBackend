package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Student student;

    @Column(precision = 5, scale = 2, nullable = false)
    @DecimalMin("0.00")       // disallow values < 0.00
    @DecimalMax("100.00")     // disallow values > 100.00
    @Digits(integer = 3, fraction = 2)  // allow up to 3 integer digits and 2 fractional digits
    private BigDecimal score;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private AppUser user;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    @JsonIgnore
    private Group group;


    public Grade() {}
    public Grade(Student student, BigDecimal score, String comment, AppUser user, LocalDateTime date) {
        this.student = student;
        this.score = score;
        this.comment = comment;
        this.user = user;
        this.date = date;
    }
}
