package org.example.controller.converters;

import org.example.domain.UserExercise;
import org.example.persistence.entity.UserExerciseEntity;

import java.util.ArrayList;
import java.util.List;

public class UserExerciseConverter {

    private UserExerciseConverter() {
    }
    public static UserExercise convert(UserExerciseEntity entity) {
        return UserExercise.builder()
                .id(entity.getId())
                .user(UserConverter.convert(entity.getUser()))
                .exercise(ExerciseConverter.convertExercise(entity.getExercise()))
                .build();
    }

    public static List<UserExercise> convertList(List<UserExerciseEntity> entities) {
        List<UserExercise> userExercises = new ArrayList<>();
        for (UserExerciseEntity entity : entities) {
            userExercises.add(convert(entity));
        }
        return userExercises;
    }

}
