package org.example.controller.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignExerciseRequest {
    private long userId;
    private long exerciseId;
    private LocalDate date;
}
