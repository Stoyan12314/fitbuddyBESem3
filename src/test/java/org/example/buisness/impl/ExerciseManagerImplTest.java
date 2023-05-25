package org.example.buisness.impl;

import org.example.controller.converters.ExerciseConverter;
import org.example.domain.Exercise;
import org.example.persistence.ExerciseRepo;
import org.example.persistence.entity.ExerciseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.stream.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseManagerImplTest {


    @Test
    void getExercisesReturnsAllExercises(){
        ExerciseRepo exerciseRepoMock = mock(ExerciseRepo.class);
        ExerciseEntity exercise1 = new ExerciseEntity();
        exercise1.setId(1L);
        ExerciseEntity exercise2 = new ExerciseEntity();
        exercise2.setId(2L);

       List<ExerciseEntity> expectedResult = List.of(exercise1,exercise2);

       when(exerciseRepoMock.findAll())
                .thenReturn(expectedResult);

       ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepoMock);
        List<Exercise> actualResult = exerciseManager.getExercises();
        List<Exercise> exercises = expectedResult.stream().map(ExerciseConverter::convertExercise).toList();

       List<Long> expectedIds = exercises.stream()
                .map(Exercise::getId)
                .collect(Collectors.toList());

        List<Long> actualIds = actualResult.stream()
                .map(Exercise::getId)
                .collect(Collectors.toList());

        assertEquals(expectedIds, actualIds);
        verify(exerciseRepoMock).findAll();
   }

    @Test
   void getExercisesReturnsEmptyList(){
        ExerciseRepo exerciseRepoMock = mock(ExerciseRepo.class);

        List<ExerciseEntity> expectedResult = new ArrayList<>();

        when(exerciseRepoMock.findAll())
                .thenReturn(expectedResult);

        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepoMock);
        List<Exercise> actualResult = exerciseManager.getExercises();
        assertEquals(expectedResult, actualResult);
        verify(exerciseRepoMock).findAll();
    }


//    @Test
//    void getExerciseByIdFound(){
//        ExerciseRepo exerciseRepoMock = mock(ExerciseRepo.class);
//
//        ExerciseEntity exercise1 = new ExerciseEntity();
//        exercise1.setId(1L);
//
//        Optional<ExerciseEntity> expectedResult = Optional.of(exercise1);
//
//        when(exerciseRepoMock.findById(1L))
//                .thenReturn(expectedResult);
//
//        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepoMock);
//        Exercise actualResult= exerciseManager.getExercise(1L);
//        assertEquals(expectedResult.get().getId(), actualResult.get().getId());
//       verify(exerciseRepoMock).findById(1L);
//    }

//    @Test
//    void getExerciseByIdNotFound() {
//        ExerciseRepo exerciseRepoMock = mock(ExerciseRepo.class);
//
//        ExerciseEntity exercise1 = new ExerciseEntity();
//        exercise1.setId(1L);
//
//        Optional<ExerciseEntity> exerciseOptional = Optional.of(exercise1);
//
//       when(exerciseRepoMock.findById(2L))
//                .thenReturn(Optional.empty()); // Updated stubbing for findById(2L)
//
//        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepoMock);
//       Exercise actualResult = exerciseManager.getExercise(2L);
//        assertEquals(Optional.empty(), actualResult);
//        verify(exerciseRepoMock).findById(2L);
//   }

    @Test
    void createExerciseSuccessful(){
        ExerciseRepo exerciseRepoMock = mock(ExerciseRepo.class);

        Exercise exercise = new Exercise();

        when(exerciseRepoMock.createExercise(exercise))
                .thenReturn(1L);

        ExerciseManagerImpl exerciseManager = new ExerciseManagerImpl(exerciseRepoMock);

        Long actualId = exerciseManager.createExercise(exercise);
        assertEquals(1L, actualId);
        verify(exerciseRepoMock).createExercise(exercise);
    }

}