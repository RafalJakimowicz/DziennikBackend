package org.example.dziennikbackend.models.DTOs;

import org.example.dziennikbackend.models.Enums.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String surname;
    private Integer albumNumber;
    private Long majorId;
    private Integer year;
    private StudentStatus studentStatus;
}
