package org.example.dziennikbackend.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dziennikbackend.models.Enums.ClassesTypes;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private Long id;
    private ClassesTypes type;
    private String code;
    private Long userId;
}
