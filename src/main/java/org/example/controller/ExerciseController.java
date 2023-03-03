package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.ExerciseManager;
import org.example.controller.RequestsResponds.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/exercises")
@AllArgsConstructor
public class ExerciseController {
    private final ExerciseManager exerciseManager;
    @GetMapping
    public ResponseEntity<GetExercisesResponse> getExercises()
    {
        return ResponseEntity.ok(exerciseManager.getExercises());
    }

    @DeleteMapping("{exerciseId}")
    public ResponseEntity<Void> deleteExercise(@PathVariable int exerciseId)
    {
        exerciseManager.deleteExerciseById(exerciseId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateExerciseResponse> createExercise(@RequestBody @Valid CreateExerciseRequest request)
    {
        CreateExerciseResponse response = exerciseManager.createExercise(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
