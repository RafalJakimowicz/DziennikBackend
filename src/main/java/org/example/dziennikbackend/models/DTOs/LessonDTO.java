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
public class LessonDTO {
    private Long id;
    private Long groupId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String subject;
    private String room;
}
