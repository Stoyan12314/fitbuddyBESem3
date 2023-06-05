package org.example.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

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