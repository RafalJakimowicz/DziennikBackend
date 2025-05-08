package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "students_in_groups")
public class StudentInGroup {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id")
    @JsonIgnore
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    @JsonIgnore
    private Group group;

    public StudentInGroup() {}
    public StudentInGroup(Student student, Group group){
        this.student = student;
        this.group = group;
    }
}
