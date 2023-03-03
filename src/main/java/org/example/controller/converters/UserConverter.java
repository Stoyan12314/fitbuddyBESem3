package org.example.controller.converters;

import org.example.domain.Exercise;
import org.example.domain.User;
import org.example.persistence.entity.ExerciseEntity;
import org.example.persistence.entity.UserEntity;

public class UserConverter {
    private UserConverter() {
    }


    public static User convert(UserEntity user)
    {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
