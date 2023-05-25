package org.example.persistence;

import org.example.controller.dto.UpdateExerciseRequest;
import org.example.domain.Exercise;
import org.example.persistence.entity.ExerciseEntity;

import java.util.List;

public interface ExerciseRepo {

    List<ExerciseEntity> findAll();

    ExerciseEntity findById(long exerciseId);

    Long createExercise(Exercise request);


    void updateExercise(UpdateExerciseRequest request);

    void deleteExercise(long exerciseId);
}
