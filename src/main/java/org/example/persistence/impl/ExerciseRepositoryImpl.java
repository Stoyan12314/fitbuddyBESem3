package org.example.persistence.impl;


import lombok.AllArgsConstructor;
import org.example.buisness.Exceptions.CreateExerciseException;
import org.example.buisness.Exceptions.DeleteExerciseException;
import org.example.buisness.Exceptions.UpdateExerciseException;
import org.example.controller.converters.ExerciseConverter;
import org.example.domain.Exercise;
import org.example.persistence.ExerciseRepo;
import org.example.persistence.JPAExerciseRepository;
import org.example.persistence.entity.ExerciseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class ExerciseRepositoryImpl implements ExerciseRepo {


    private final JPAExerciseRepository jpaExerciseRepository;
    @Override
    public List<ExerciseEntity> findAll()
    {
        return jpaExerciseRepository.findAll().stream().toList();

    }

    @Override
    public Optional<ExerciseEntity> findById(long exerciseId) {
        return jpaExerciseRepository.findById(exerciseId);
    }

    @Override
    public Long createExercise(Exercise request) {




        ExerciseEntity exerciseEntity = ExerciseConverter.convertToEntity(request);



        try {
            return jpaExerciseRepository.save(exerciseEntity).getId();
        }
        catch (Exception e){
            throw new CreateExerciseException("Error while creating exercise");
        }
    }

    @Override
    public void updateExercise(Exercise exercise) {
        ExerciseEntity exerciseEntity = ExerciseConverter.convertToEntity(exercise);
        try {
            jpaExerciseRepository.save(exerciseEntity);
        }
        catch (Exception e){
            throw new UpdateExerciseException("Error while updating exercise");
        }
    }

    @Override
    public void deleteExercise(long exerciseId) {
        try {
            jpaExerciseRepository.deleteById(exerciseId);
        }
        catch (Exception e){
            throw new DeleteExerciseException("Error while deleting the exercise");
        }
    }

}
