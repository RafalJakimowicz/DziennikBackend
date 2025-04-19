package org.example.dziennikbackend.models.Entities;

import jakarta.persistence.*;
import org.example.dziennikbackend.models.Enums.ClassesTypes;
import org.example.dziennikbackend.models.Enums.StudentStatus;

import java.util.List;

@Entity
public class CoursePart {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_edition_id", nullable = false)
    private CourseEdition edition;

    @OneToMany(mappedBy = "coursePart")
    private List<Group> groups;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private ClassesTypes type;

    private String description;

    private Short ects;

    public CoursePart() {}
    public CoursePart(CourseEdition edition, ClassesTypes type, String description, Short ects){
        this.edition = edition;
        this.type = type;
        this.description = description;
        this.ects = ects;
    }

    public void removeGroup(Group group){
        this.groups.remove(group);
        group.setCoursePart(null);
    }

    public void addGroup(Group group){
        this.groups.add(group);
        group.setCoursePart(this);
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return this.groups;
    }

    public Long getId() {
        return this.id;
    }

    public CourseEdition getEdition(){
        return this.edition;
    }

    public void setEdition(CourseEdition edition){
        this.edition = edition;
    }

    public ClassesTypes getType(){
        return this.type;
    }

    public void setType(ClassesTypes type){
        this.type = type;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Short getEcts(){
        return this.ects;
    }

    public void setEcts(Short ects){
        this.ects = ects;
    }
}
