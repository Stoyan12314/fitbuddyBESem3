package org.example.controller.converters;

import org.example.controller.RequestsResponds.GetUserResponse;
import org.example.domain.Exercise;
import org.example.domain.User;
import org.example.persistence.entity.ExerciseEntity;
import org.example.persistence.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

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

    public static ResponseEntity<GetUserResponse> convert(Optional<UserEntity> optionalUserEntity) {
        if (optionalUserEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = convert(optionalUserEntity.get());
        GetUserResponse response = GetUserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
        return ResponseEntity.ok(response);
    }
}
