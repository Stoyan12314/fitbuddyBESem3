package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.ExerciseManager;
import org.example.controller.RequestsResponds.CreateExerciseRequest;
import org.example.controller.RequestsResponds.CreateExerciseResponse;
import org.example.controller.RequestsResponds.GetExercisesResponse;
import org.example.controller.converters.ExerciseConverter;
import org.example.domain.Exercise;
import org.example.persistence.ExerciseRepo;
import org.example.persistence.entity.ExerciseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExerciseManagerImpl implements ExerciseManager {

    private ExerciseRepo exerciseRepo;

    @Override
    public Optional<Exercise> getExercise(long id) {
        return exerciseRepo.findById(id).map(ExerciseConverter::convertExercise);
    }

    @Override
    public List<Exercise> getExercises() {
        return exerciseRepo.findAll().stream().map(ExerciseConverter::convertExercise).toList();
    }

    @Override
    public void deleteExerciseById(long exerciseId) {
        exerciseRepo.deleteExercise(exerciseId);
    }

    @Override
    public Long createExercise(Exercise request) {
        return exerciseRepo.createExercise(request);
    }

    @Override
    public void updateExercise(Exercise exercise) {
        exerciseRepo.updateExercise(exercise);
    }

    @Override
    public void deleteExercise(long exerciseId) {
        exerciseRepo.deleteExercise(exerciseId);
    }
}
