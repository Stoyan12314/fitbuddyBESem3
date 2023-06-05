package org.example.buisness;


import org.example.controller.dto.UpdateExerciseRequest;
import org.example.domain.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseManager {
    Exercise getExercise(long id);


    void deleteExerciseById(long exerciseId);
    Page<Exercise> getExercises(String name, Pageable pageable);
    Long createExercise(Exercise request);
    void updateExercise(UpdateExerciseRequest exercise);
    void deleteExercise(long exerciseId);

}
