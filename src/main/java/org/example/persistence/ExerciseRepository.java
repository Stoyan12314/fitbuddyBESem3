package org.example.persistence;

import org.example.persistence.entity.ExerciseEntity;

import java.util.List;

public interface ExerciseRepository {

    List<ExerciseEntity> findAll();

    void deleteById(long countryId);

    ExerciseEntity saveExercise(ExerciseEntity exercise);
}
