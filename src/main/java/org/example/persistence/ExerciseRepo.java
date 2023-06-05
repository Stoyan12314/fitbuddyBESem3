package org.example.persistence;

import org.example.controller.dto.UpdateExerciseRequest;
import org.example.domain.Exercise;
import org.example.persistence.entity.ExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseRepo {

    Page<ExerciseEntity> findAll(Pageable pageable);

    ExerciseEntity findById(long exerciseId);

    Long createExercise(Exercise request);
    Page<ExerciseEntity> findByName(String name, Pageable pageable);
    void updateExercise(UpdateExerciseRequest request);

    void deleteExercise(long exerciseId);
}
