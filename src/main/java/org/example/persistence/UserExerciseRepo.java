package org.example.persistence;

import org.example.domain.UserExercise;
import org.example.persistence.entity.UserExerciseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserExerciseRepo {

    void delete(UserExerciseEntity userExerciseEntity);
    Optional<UserExerciseEntity> findExerciseById(long exerciseId);
    List<UserExerciseEntity> getUserExercises(long userId, LocalDate date);
    void save(UserExerciseEntity userExerciseEntity);

    List<UserExerciseEntity> getAllUserExercises(long userId);
}