package org.example.dziennikbackend.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.dziennikbackend.models.Enums.ClassesTypes;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue
    private Long id;

    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private AppUser user;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'WYKLAD'")
    private ClassesTypes type;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Group() {}
    public Group(String code, AppUser user){
        this.code = code;
        this.user = user;
    }

}
