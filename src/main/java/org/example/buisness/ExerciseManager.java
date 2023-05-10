package org.example.buisness;

import org.example.controller.RequestsResponds.CreateExerciseRequest;
import org.example.controller.RequestsResponds.CreateExerciseResponse;
import org.example.controller.RequestsResponds.GetExercisesResponse;
import org.example.domain.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseManager {
    Optional<Exercise> getExercise(long id);
    List<Exercise> getExercises();

    void deleteExerciseById(long exerciseId);

    Long createExercise(Exercise request);
    void updateExercise(Exercise exercise);
    void deleteExercise(long exerciseId);
}
