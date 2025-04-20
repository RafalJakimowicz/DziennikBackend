package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_part_id")
    private CoursePart coursePart;

    private String code;

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser user;

    @OneToMany(mappedBy = "group")
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "group")
    private List<StudentInGroup> studentInGroups;

    @OneToMany(mappedBy = "group")
    private List<Grade> grades;

    public Group() {}
    public Group(CoursePart part, String code, AppUser user){
        this.coursePart = part;
        this.code = code;
        this.user = user;
    }

    public void removeGrade(Grade grade){
        this.grades.remove(grade);
        grade.setGroup(null);
    }

    public void addGrade(Grade grade){
        this.grades.add(grade);
        grade.setGroup(this);
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Grade> getGrades() {
        return this.grades;
    }

    public void removeStudentInGroup(StudentInGroup sg){
        this.studentInGroups.remove(sg);
        sg.setGroup(null);
    }

    public void addStudentInGroup(StudentInGroup sg){
        this.studentInGroups.add(sg);
        sg.setGroup(this);
    }

    public void setStudentInGroups(List<StudentInGroup> studentInGroups) {
        this.studentInGroups = studentInGroups;
    }

    public List<StudentInGroup> getStudentInGroups() {
        return this.studentInGroups;
    }

    public void removeLesson(Lesson lesson){
        this.lessons.remove(lesson);
        lesson.setGroup(null);
    }

    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
        lesson.setGroup(this);
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Lesson> getLessons() {
        return this.lessons;
    }

    public Long getId(){
        return this.id;
    }

    public void setCoursePart(CoursePart part){
        this.coursePart = part;
    }

    public CoursePart getCoursePart(){
        return this.coursePart;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public AppUser getUser(){
        return this.user;
    }

    public void setUser(AppUser user){
        this.user = user;
    }
}
