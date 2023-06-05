package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.example.buisness.UserExerciseManager;
import org.example.buisness.exceptions.ExerciseNotFoundException;
import org.example.buisness.exceptions.UserNotFoundException;
import org.example.controller.converters.UserExerciseConverter;
import org.example.domain.UserExercise;
import org.example.persistence.ExerciseRepo;
import org.example.persistence.UserExerciseRepo;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.ExerciseEntity;
import org.example.persistence.entity.UserEntity;
import org.example.persistence.entity.UserExerciseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserExerciseManagerImpl implements UserExerciseManager {
    private UserExerciseRepo userExerciseRepo;
    private UserRepository userRepo;
    private ExerciseRepo exerciseRepo;
    @Override
    public List<UserExercise> getUserExercises(long userId, LocalDate date) {
        return UserExerciseConverter.convertList((userExerciseRepo.getUserExercises(userId, date)));
    }
    @Override
    public void unassignExerciseFromUser(long exerciseId) {
        UserExerciseEntity userExerciseEntity = userExerciseRepo.findExerciseById(exerciseId)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise not found with id: " + exerciseId));

        userExerciseRepo.delete(userExerciseEntity);
    }
    @Override
    public void assignExerciseToUser(long userId, long exerciseId, LocalDate date) {
        UserEntity userEntity = userRepo.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        ExerciseEntity exerciseEntity = exerciseRepo.findById(exerciseId);


        UserExerciseEntity userExerciseEntity = new UserExerciseEntity();
        userExerciseEntity.setUser(userEntity);
        userExerciseEntity.setExercise(exerciseEntity);
        userExerciseEntity.setDate(date);

        userExerciseRepo.save(userExerciseEntity);

    }

    @Override
    public List<UserExercise> getAllUserExercises(long userId) {
        return UserExerciseConverter.convertList((userExerciseRepo.getAllUserExercises(userId)));

    }
}
