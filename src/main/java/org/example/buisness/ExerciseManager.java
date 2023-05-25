package org.example.buisness;


import org.example.controller.dto.UpdateExerciseRequest;
import org.example.domain.Exercise;

import java.util.List;

public interface ExerciseManager {
    Exercise getExercise(long id);
    List<Exercise> getExercises();

    void deleteExerciseById(long exerciseId);

    Long createExercise(Exercise request);
    void updateExercise(UpdateExerciseRequest exercise);
    void deleteExercise(long exerciseId);
}
