package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.dziennikbackend.models.Enums.ClassesTypes;
import org.example.dziennikbackend.models.Enums.StudentStatus;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "course_parts")
public class CoursePart {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_edition_id", nullable = false)
    @JsonIgnore
    private CourseEdition edition;

    @OneToMany(
            mappedBy = "coursePart",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Group> groups = new ArrayList<>();

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
}
