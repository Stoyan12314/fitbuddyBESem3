
package org.example.persistence.impl;


import lombok.AllArgsConstructor;
import org.example.controller.converters.ExerciseConverter;
import org.example.controller.dto.UpdateExerciseRequest;
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
    public ExerciseEntity findById(long exerciseId) {

        Optional<ExerciseEntity> exercise = jpaExerciseRepository.findById(exerciseId);
        if (exercise.isPresent()) {
            return exercise.get();
        } else {
            return null;
        }
    }

    @Override
    public Long createExercise(Exercise request) {




        ExerciseEntity exerciseEntity = ExerciseConverter.convertToEntity(request);

        return jpaExerciseRepository.save(exerciseEntity).getId();

    }

    @Override
    public void updateExercise(UpdateExerciseRequest exercise) {
        ExerciseEntity exerciseEntity = ExerciseConverter.convertUpdate(exercise);
        jpaExerciseRepository.save(exerciseEntity);

    }

    @Override
    public void deleteExercise(long exerciseId) {
        jpaExerciseRepository.deleteById(exerciseId);

    }

}
