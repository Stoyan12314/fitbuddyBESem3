package org.example.controller.converters;

import org.example.controller.dto.CreateExerciseRequest;
import org.example.controller.dto.UpdateExerciseRequest;
import org.example.domain.Exercise;
import org.example.persistence.entity.ExerciseEntity;

public class ExerciseConverter {
    private ExerciseConverter() {
    }


    public static ExerciseEntity convertUpdate(UpdateExerciseRequest exercise) {
        return ExerciseEntity.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .imageUrl(exercise.getImageUrl()) // Add imageUrl
                .description(exercise.getDescription())
                .build();
    }
    public static Exercise convert(CreateExerciseRequest exercise) {
        return Exercise.builder()

                .name(exercise.getName())
               // .imageUrl(exercise.getImageUrl()) // Add imageUrl

                .description(exercise.getDescription())
                .build();
    }


    public static Exercise convertExercise(ExerciseEntity exercise) {
        return Exercise.builder()
                .id(exercise.getId())
                .imageUrl(exercise.getImageUrl()) // Add imageUrl

                .name(exercise.getName())
                .description(exercise.getDescription())
                .build();
    }

    public static ExerciseEntity convertToEntity(Exercise exercise) {
        return ExerciseEntity.builder()
                .id(exercise.getId())
                .imageUrl(exercise.getImageUrl()) // Add imageUrl

                .name(exercise.getName())
                .description(exercise.getDescription())
                .build();
    }
}
