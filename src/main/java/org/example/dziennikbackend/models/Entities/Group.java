package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;

@Entity
public class Group {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="part_of_course_id")
    private CoursePart partOfCourse;

    private String code;

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser user;

    public Group() {}
    public Group(CoursePart part, String code, AppUser user){
        this.partOfCourse = part;
        this.code = code;
        this.user = user;
    }

    public Long getId(){
        return this.id;
    }

    public void setPartOfCourse(CoursePart part){
        this.partOfCourse = part;
    }

    public CoursePart getPartOfCourse(){
        return this.partOfCourse;
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
