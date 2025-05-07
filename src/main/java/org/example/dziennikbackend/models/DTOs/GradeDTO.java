package org.example.dziennikbackend.models.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long id;
    private Long studentId;
    private BigDecimal score;
    private String comment;
    private Long userId;
    private LocalDateTime date;
    private Long groupId;
}
