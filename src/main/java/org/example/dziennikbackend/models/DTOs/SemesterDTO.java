package org.example.dziennikbackend.models.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDTO {
    private Long id;
    private String code;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
