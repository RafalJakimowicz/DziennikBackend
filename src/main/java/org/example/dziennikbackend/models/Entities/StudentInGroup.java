package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;

@Entity
public class StudentInGroup {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    public StudentInGroup() {}
    public StudentInGroup(Student student, Group group){
        this.student = student;
        this.group = group;
    }

    public Long getId() {
        return this.id;
    }

    public Student getStudent(){
        return this.student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public Group getGroup(){
        return this.group;
    }

    public void setGroup(Group group){
        this.group = group;
    }
}
