package org.example.dziennikbackend.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MajorDTO {
    private Long id;
    private String name;
    private String shortName;
}
