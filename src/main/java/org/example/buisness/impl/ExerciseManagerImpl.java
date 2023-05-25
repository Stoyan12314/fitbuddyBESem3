package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.exceptions.CreateExerciseException;
import org.example.buisness.exceptions.NotFoundExerciseException;
import org.example.buisness.ExerciseManager;
import org.example.controller.converters.ExerciseConverter;
import org.example.controller.dto.UpdateExerciseRequest;
import org.example.domain.Exercise;
import org.example.persistence.ExerciseRepo;
import org.example.persistence.entity.ExerciseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseManagerImpl implements ExerciseManager {

    private ExerciseRepo exerciseRepo;


    @Override
    public Exercise getExercise(long id) {
            ExerciseEntity   exercise =  exerciseRepo.findById(id);
            if(exercise == null)
            {
                throw new NotFoundExerciseException();
            }
            return ExerciseConverter.convertExercise(exercise);
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
    public Long createExercise(Exercise request)
    {
        Long id = exerciseRepo.createExercise(request);
        if (id == null)
        {
            throw new CreateExerciseException();
        }
        return id;
    }

    @Override
    public void updateExercise(UpdateExerciseRequest exercise) {

        exerciseRepo.updateExercise(exercise);
    }

    @Override
    public void deleteExercise(long exerciseId) {
        exerciseRepo.deleteExercise(exerciseId);
    }
}
