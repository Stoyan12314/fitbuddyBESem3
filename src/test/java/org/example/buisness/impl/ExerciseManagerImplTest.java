package org.example.buisness.impl;

import org.example.buisness.exceptions.CreateExerciseException;
import org.example.buisness.exceptions.NotFoundExerciseException;
import org.example.buisness.impl.ExerciseManagerImpl;
import org.example.controller.dto.UpdateExerciseRequest;
import org.example.controller.converters.ExerciseConverter;
import org.example.domain.Exercise;
import org.example.persistence.ExerciseRepo;
import org.example.persistence.entity.ExerciseEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseManagerImplTest {

    @Test
    @DisplayName("Should return an exercise by ID")
    void getExerciseTest() {
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepo);
        long id = 1L;
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        when(exerciseRepo.findById(id)).thenReturn(exerciseEntity);
        Exercise result = exerciseManager.getExercise(id);
        assertNotNull(result);
        assertEquals(ExerciseConverter.convertExercise(exerciseEntity).getId(), result.getId());
    }

    @Test
    @DisplayName("Should throw NotFoundExerciseException when exercise does not exist")
    void getExerciseNotFoundTest() {
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepo);
        long id = 1L;
        when(exerciseRepo.findById(id)).thenReturn(null);
        assertThrows(NotFoundExerciseException.class, () -> exerciseManager.getExercise(id));
    }

    @Test
    @DisplayName("Should delete an exercise by ID")
    void deleteExerciseByIdTest() {
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepo);
        long id = 1L;
        doNothing().when(exerciseRepo).deleteExercise(id);
        assertDoesNotThrow(() -> exerciseManager.deleteExerciseById(id));
        verify(exerciseRepo, times(1)).deleteExercise(id);
    }

    @Test
    @DisplayName("Should create a new exercise")
    void createExerciseTest() {
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepo);
        Exercise exercise = new Exercise();
        when(exerciseRepo.createExercise(exercise)).thenReturn(1L);
        assertDoesNotThrow(() -> exerciseManager.createExercise(exercise));
    }

    @Test
    @DisplayName("Should throw CreateExerciseException when unable to create exercise")
    void createExerciseExceptionTest() {
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepo);
        Exercise exercise = new Exercise();
        when(exerciseRepo.createExercise(exercise)).thenReturn(null);
        assertThrows(CreateExerciseException.class, () -> exerciseManager.createExercise(exercise));
    }

    @Test
    @DisplayName("Should update an exercise")
    void updateExerciseTest() {
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepo);
        UpdateExerciseRequest request = new UpdateExerciseRequest();
        doNothing().when(exerciseRepo).updateExercise(request);
        assertDoesNotThrow(() -> exerciseManager.updateExercise(request));
        verify(exerciseRepo, times(1)).updateExercise(request);
    }

    @Test
    @DisplayName("Should delete an exercise")
    void deleteExerciseTest() {
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepo);
        long id = 1L;
        doNothing().when(exerciseRepo).deleteExercise(id);
        assertDoesNotThrow(() -> exerciseManager.deleteExercise(id));
        verify(exerciseRepo, times(1)).deleteExercise(id);
    }

    @Test
    @DisplayName("Should get all exercises")
    void getExercisesTest() {
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepo);
        Pageable pageable = PageRequest.of(0, 10);
        List<ExerciseEntity> exerciseEntityList = new ArrayList<>();
        Page<ExerciseEntity> exerciseEntityPage = new PageImpl<>(exerciseEntityList);
        when(exerciseRepo.findAll(pageable)).thenReturn(exerciseEntityPage);
        Page<Exercise> result = exerciseManager.getExercises("", pageable);
        assertNotNull(result);
        assertEquals(ExerciseConverter.convertList(exerciseEntityPage), result);
    }

    @Test
    @DisplayName("Should find exercises by name")
    void getExercisesByNameTest() {
        ExerciseRepo exerciseRepo = mock(ExerciseRepo.class);
        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepo);
        String name = "Test";
        Pageable pageable = PageRequest.of(0, 10);
        List<ExerciseEntity> exerciseEntityList = new ArrayList<>();
        Page<ExerciseEntity> exerciseEntityPage = new PageImpl<>(exerciseEntityList);
        when(exerciseRepo.findByName(name, pageable)).thenReturn(exerciseEntityPage);
        Page<Exercise> result = exerciseManager.getExercises(name, pageable);
        assertNotNull(result);
        assertEquals(ExerciseConverter.convertList(exerciseEntityPage), result);
    }
}
