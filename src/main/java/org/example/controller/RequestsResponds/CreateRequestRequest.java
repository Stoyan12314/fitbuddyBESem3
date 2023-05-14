package org.example.controller.RequestsResponds;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateRequestRequest {

    private LocalDate date;
    private Long userId;
    private Long exerciseId;
    private Integer reps;
    private Double weight;

}