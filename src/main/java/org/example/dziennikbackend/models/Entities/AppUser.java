package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name="users")
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CourseEdition> courseEditions = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Attendance> attendances = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Group> groups = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Grade> grades = new ArrayList<>();

    public AppUser() {}
    public AppUser(String name, String surname, String email, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public void removeGrade(Grade grade){
        this.grades.remove(grade);
        grade.setUser(null);
    }

    public void addGrade(Grade grade){
        this.grades.add(grade);
        grade.setUser(this);
    }

    public void setGrade(List<Grade> grade) {
        this.grades = grade;
    }

    public List<Grade> getGrade() {
        return this.grades;
    }

    public void removeGroup(Group group){
        this.groups.remove(group);
        group.setUser(null);
    }

    public void addGroup(Group group){
        this.groups.add(group);
        group.setUser(this);
    }


    public void removeAttendance(Attendance attendance){
        this.attendances.remove(attendance);
        attendance.setUser(null);
    }

    public void addAttendance(Attendance attendance){
        this.attendances.add(attendance);
        attendance.setUser(this);
    }

    public void removeCourseEdition(CourseEdition edition){
        this.courseEditions.remove(edition);
        edition.setUser(null);
    }

    public void addCourseEdition(CourseEdition edition){
        this.courseEditions.add(edition);
        edition.setUser(this);
    }
}
