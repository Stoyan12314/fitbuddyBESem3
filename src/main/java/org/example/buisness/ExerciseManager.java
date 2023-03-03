package org.example.buisness;

import org.example.controller.RequestsResponds.CreateExerciseRequest;
import org.example.controller.RequestsResponds.CreateExerciseResponse;
import org.example.controller.RequestsResponds.GetExercisesResponse;

public interface ExerciseManager {
    GetExercisesResponse getExercises();

    void deleteExerciseById(long exerciseId);

    CreateExerciseResponse createExercise(CreateExerciseRequest request);
}
