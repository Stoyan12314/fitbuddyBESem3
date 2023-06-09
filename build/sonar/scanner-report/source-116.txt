package org.example.buisness.impl;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserExerciseManagerImplTest {
    @Test
    @DisplayName("Should return user exercises for a particular date")
    void getUserExercisesTest() {
        UserExerciseRepo userExerciseRepo = mock(UserExerciseRepo.class);
        UserRepository userRepo = mock(UserRepository.class);
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        UserExerciseManagerImpl userExerciseManager =
                new UserExerciseManagerImpl(userExerciseRepo, userRepo, exerciseRepo);

        long userId = 1L;
        LocalDate date = LocalDate.now();

        UserEntity user = mock(UserEntity.class);
        when(user.getId()).thenReturn(userId);

        ExerciseEntity exercise = mock(ExerciseEntity.class);

        UserExerciseEntity userExerciseEntity1 = new UserExerciseEntity();
        userExerciseEntity1.setUser(user);
        userExerciseEntity1.setExercise(exercise);
        userExerciseEntity1.setDate(date);

        UserExerciseEntity userExerciseEntity2 = new UserExerciseEntity();
        userExerciseEntity2.setUser(user);
        userExerciseEntity2.setExercise(exercise);
        userExerciseEntity2.setDate(date);

        List<UserExerciseEntity> exerciseEntities = Arrays.asList(userExerciseEntity1, userExerciseEntity2);

        when(userExerciseRepo.getUserExercises(userId, date)).thenReturn(exerciseEntities);

        List<UserExercise> result = userExerciseManager.getUserExercises(userId, date);

        assertNotNull(result);
        assertEquals(exerciseEntities.size(), result.size());
    }


    @Test
    @DisplayName("Should return empty list when no exercises found for a particular date")
    void getUserExercisesNoExercisesTest() {
        UserExerciseRepo userExerciseRepo = mock(UserExerciseRepo.class);
        UserRepository userRepo = mock(UserRepository.class);
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        UserExerciseManagerImpl userExerciseManager =
                new UserExerciseManagerImpl(userExerciseRepo, userRepo, exerciseRepo);

        long userId = 1L;
        LocalDate date = LocalDate.now();

        when(userExerciseRepo.getUserExercises(userId, date)).thenReturn(Collections.emptyList());

        List<UserExercise> result = userExerciseManager.getUserExercises(userId, date);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    @DisplayName("Should assign exercise to user")
    void assignExerciseToUserTest() {
        UserExerciseRepo userExerciseRepo = mock(UserExerciseRepo.class);
        UserRepository userRepo = mock(UserRepository.class);
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        UserExerciseManagerImpl userExerciseManager =
                new UserExerciseManagerImpl(userExerciseRepo, userRepo, exerciseRepo);

        long userId = 1L;
        long exerciseId = 1L;
        LocalDate date = LocalDate.now();

        UserEntity userEntity = new UserEntity();
        ExerciseEntity exerciseEntity = new ExerciseEntity();

        when(userRepo.findUserById(userId)).thenReturn(Optional.of(userEntity));
        when(exerciseRepo.findById(exerciseId)).thenReturn(exerciseEntity);

        assertDoesNotThrow(() -> userExerciseManager.assignExerciseToUser(userId, exerciseId, date));

        verify(userExerciseRepo, times(1)).save(any(UserExerciseEntity.class));
    }

    @Test
    @DisplayName("Should unassign exercise from user")
    void unassignExerciseFromUserTest() {
        UserExerciseRepo userExerciseRepo = mock(UserExerciseRepo.class);
        UserRepository userRepo = mock(UserRepository.class);
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        UserExerciseManagerImpl userExerciseManager =
                new UserExerciseManagerImpl(userExerciseRepo, userRepo, exerciseRepo);

        long exerciseId = 1L;
        UserExerciseEntity userExerciseEntity = new UserExerciseEntity();

        when(userExerciseRepo.findExerciseById(exerciseId)).thenReturn(Optional.of(userExerciseEntity));

        assertDoesNotThrow(() -> userExerciseManager.unassignExerciseFromUser(exerciseId));
        verify(userExerciseRepo, times(1)).delete(userExerciseEntity);
    }

    @Test
    @DisplayName("Should throw exception when unassigning non-existent exercise")
    void unassignExerciseFromUserNotFoundTest() {
        UserExerciseRepo userExerciseRepo = mock(UserExerciseRepo.class);
        UserRepository userRepo = mock(UserRepository.class);
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        UserExerciseManagerImpl userExerciseManager =
                new UserExerciseManagerImpl(userExerciseRepo, userRepo, exerciseRepo);

        long exerciseId = 1L;

        when(userExerciseRepo.findExerciseById(exerciseId)).thenReturn(Optional.empty());

        assertThrows(ExerciseNotFoundException.class, () -> userExerciseManager.unassignExerciseFromUser(exerciseId));
    }


    @Test
    @DisplayName("Should throw UserNotFoundException when user does not exist")
    void assignExerciseToUserUserNotFoundTest() {
        UserExerciseRepo userExerciseRepo = mock(UserExerciseRepo.class);
        UserRepository userRepo = mock(UserRepository.class);
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        UserExerciseManagerImpl userExerciseManager =
                new UserExerciseManagerImpl(userExerciseRepo, userRepo, exerciseRepo);

        long userId = 1L;
        long exerciseId = 1L;
        LocalDate date = LocalDate.now();

        when(userRepo.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userExerciseManager.assignExerciseToUser(userId, exerciseId, date));
    }

    @Test
    @DisplayName("Should return all user exercises")
    void getAllUserExercisesTest() {
        UserExerciseRepo userExerciseRepo = mock(UserExerciseRepo.class);
        UserRepository userRepo = mock(UserRepository.class);
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        UserExerciseManagerImpl userExerciseManager =
                new UserExerciseManagerImpl(userExerciseRepo, userRepo, exerciseRepo);

        long userId = 1L;

        UserEntity user = mock(UserEntity.class);
        when(user.getId()).thenReturn(userId);

        ExerciseEntity exercise = mock(ExerciseEntity.class);

        UserExerciseEntity userExerciseEntity1 = new UserExerciseEntity();
        userExerciseEntity1.setUser(user);
        userExerciseEntity1.setExercise(exercise);

        UserExerciseEntity userExerciseEntity2 = new UserExerciseEntity();
        userExerciseEntity2.setUser(user);
        userExerciseEntity2.setExercise(exercise);

        List<UserExerciseEntity> exerciseEntities = Arrays.asList(userExerciseEntity1, userExerciseEntity2);

        when(userExerciseRepo.getAllUserExercises(userId)).thenReturn(exerciseEntities);

        List<UserExercise> result = userExerciseManager.getAllUserExercises(userId);

        assertNotNull(result);
        assertEquals(exerciseEntities.size(), result.size());
    }

}