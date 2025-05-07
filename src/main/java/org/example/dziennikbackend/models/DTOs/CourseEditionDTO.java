package org.example.dziennikbackend.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEditionDTO {
    private Long id;
    private Long courseId;
    private Long semesterId;
    private Long userId;
}
