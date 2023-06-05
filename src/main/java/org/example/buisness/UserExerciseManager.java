package org.example.buisness;

import org.example.domain.User;
import org.example.domain.UserExercise;

import java.time.LocalDate;
import java.util.List;

public interface UserExerciseManager {
    void unassignExerciseFromUser(long exerciseId);
    List<UserExercise> getUserExercises(long userId, LocalDate date);
    void assignExerciseToUser(long userId, long exerciseId, LocalDate date);

    List<UserExercise> getAllUserExercises(long userId);
}
