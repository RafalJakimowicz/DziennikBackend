package org.example.dziennikbackend.models.DTOs;
import org.example.dziennikbackend.models.Enums.ClassesTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoursePartDTO {
    private Long id;
    private Long courseEditionId;
    private ClassesTypes type;
    private String description;
    private Short ects;
}
