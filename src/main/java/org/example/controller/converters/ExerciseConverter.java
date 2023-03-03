package org.example.controller.converters;

import org.example.domain.Exercise;
import org.example.persistence.entity.ExerciseEntity;

public class ExerciseConverter {
    private ExerciseConverter() {
    }


    public static Exercise convert(ExerciseEntity exercise) {
        return Exercise.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .picture(exercise.getPicture())
                .build();
    }
}
