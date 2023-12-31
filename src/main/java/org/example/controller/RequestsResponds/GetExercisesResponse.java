package org.example.controller.RequestsResponds;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.Exercise;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetExercisesResponse {
    private List<Exercise> exercises;
}
