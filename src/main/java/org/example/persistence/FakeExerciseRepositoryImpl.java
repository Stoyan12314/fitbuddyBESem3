package org.example.persistence;

import org.example.persistence.entity.ExerciseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@Repository
public class FakeExerciseRepositoryImpl implements ExerciseRepository
{
    private static long NEXT_ID = 1;

    private final List<ExerciseEntity> savedExercises;

    public FakeExerciseRepositoryImpl() {
        this.savedExercises = new ArrayList<>();
    }


    @Override
    public List<ExerciseEntity> findAll()
    {
        return Collections.unmodifiableList(savedExercises);
    }

    @Override
    public void deleteById(long exerciseId)
    {
        Iterator<ExerciseEntity> iterator = savedExercises.iterator();
        while (iterator.hasNext()) {
            ExerciseEntity exercise = iterator.next();
            if (exercise.getId().equals(exerciseId)) {
                iterator.remove();
            }

        }
    }

    @Override
    public ExerciseEntity saveExercise(ExerciseEntity exercise) {
        if (exercise.getId() == null) {
            exercise.setId(NEXT_ID);
            NEXT_ID++;
            this.savedExercises.add(exercise);
        }
        return exercise;
    }




}
