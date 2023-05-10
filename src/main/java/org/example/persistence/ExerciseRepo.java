package org.example.persistence;

import org.example.domain.Exercise;
import org.example.persistence.entity.ExerciseEntity;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepo {

    List<ExerciseEntity> findAll();

    Optional<ExerciseEntity> findById(long exerciseId);

    Long createExercise(Exercise request);


    void updateExercise(Exercise request);

    void deleteExercise(long exerciseId);
}
