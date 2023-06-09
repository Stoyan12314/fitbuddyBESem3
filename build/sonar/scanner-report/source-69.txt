package org.example.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseCount {
    private Long exerciseId;
    private String exerciseName;
    private Long count;

    public ExerciseCount(String exerciseName, Long count) {
        this.exerciseName = exerciseName;
        this.count = count;
    }

}
