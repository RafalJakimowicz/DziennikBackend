package org.example.dziennikbackend.models.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInGroupDTO {
    private Long id;
    private Long studentId;
    private Long groupId;
}
