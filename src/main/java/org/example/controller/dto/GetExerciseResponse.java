package org.example.controller.dto;

import lombok.*;
import org.example.domain.Exercise;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetExerciseResponse {
    private Exercise exercise;
}
