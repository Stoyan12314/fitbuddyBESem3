package org.example.controller;

import org.example.buisness.ExerciseManager;
import org.example.controller.RequestsResponds.CreateExerciseRequest;
import org.example.controller.RequestsResponds.CreateExerciseResponse;
import org.example.controller.RequestsResponds.GetExercisesResponse;
import org.example.domain.Exercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExerciseControllerTest {

    @Mock
    private ExerciseManager exerciseManager;

    private ExerciseController exerciseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        exerciseController = new ExerciseController(exerciseManager);
    }

    @Test
    void testGetExercises() {
        // Create mock response
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise(1L, "exercise1", "description1", "123"));
        exercises.add(new Exercise(2L, "exercise2", "description2", "333"));
        GetExercisesResponse expectedResponse = new GetExercisesResponse(exercises);

        // Mock exerciseManager's getExercises() method
        when(exerciseManager.getExercises()).thenReturn(expectedResponse);

        // Call controller's getExercises() method
        ResponseEntity<GetExercisesResponse> actualResponse = exerciseController.getExercises();

        // Verify response matches expected response
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    void testDeleteExercise() {
        int exerciseId = 1;

        // Mock exerciseManager's deleteExerciseById() method
        doNothing().when(exerciseManager).deleteExerciseById(exerciseId);

        // Call controller's deleteExercise() method
        ResponseEntity<Void> actualResponse = exerciseController.deleteExercise(exerciseId);

        // Verify response matches expected response
        assertEquals(HttpStatus.NO_CONTENT, actualResponse.getStatusCode());

        // Verify that deleteExerciseById was called with the correct input
        verify(exerciseManager, times(1)).deleteExerciseById(exerciseId);
    }

    @Test
    void testCreateExercise() {
        CreateExerciseRequest request = new CreateExerciseRequest(1L,"exercise1", "description1", "123");

        // Mock exerciseManager's createExercise() method
        CreateExerciseResponse expectedResponse =  CreateExerciseResponse.builder()
                .status("Done")
                .build();


        when(exerciseManager.createExercise(request)).thenReturn(expectedResponse);

        // Call controller's createExercise() method
        ResponseEntity<CreateExerciseResponse> actualResponse = exerciseController.createExercise(request);

        // Verify response matches expected response
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }

}