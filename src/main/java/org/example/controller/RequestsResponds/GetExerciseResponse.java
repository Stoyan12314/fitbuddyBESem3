package org.example.controller.RequestsResponds;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.Exercise;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetExerciseResponse {
    private Optional<Exercise> exercise;
}
