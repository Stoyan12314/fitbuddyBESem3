package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.ExerciseManager;
import org.example.buisness.impl.CloudinaryManagerImpl;
import org.example.controller.dto.*;
import org.example.controller.converters.ExerciseConverter;
import org.example.domain.Exercise;
import org.example.security.isauthenticated.IsAuthenticated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/exercises")
@AllArgsConstructor
public class ExerciseController {
    private final ExerciseManager exerciseManager;
    private final CloudinaryManagerImpl cloudinary;

    @GetMapping("/{exerciseId}")
    public ResponseEntity<GetExerciseResponse> getExercise(@PathVariable int exerciseId)
    {
        Exercise exercise = exerciseManager.getExercise(exerciseId);
        if (exercise == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            GetExerciseResponse response = new GetExerciseResponse(exercise);

            return ResponseEntity.ok(response);
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMINISTRATION", "ROLE_TRAINER"})
    @GetMapping
    public ResponseEntity<GetExercisesResponse> getExercises(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<Exercise> exercises = exerciseManager.getExercises(name, pageable);

        if (exercises.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            GetExercisesResponse response = new GetExercisesResponse(exercises.getContent(), exercises.getTotalPages());
            return ResponseEntity.ok(response);
        }
    }
    @IsAuthenticated
    @RolesAllowed("ROLE_ADMINISTRATION")
    @DeleteMapping("{exerciseId}")
    public ResponseEntity<Void> deleteExercise(@PathVariable int exerciseId)
    {
        exerciseManager.deleteExerciseById(exerciseId);
        return ResponseEntity.noContent().build();
    }
    @IsAuthenticated
    @RolesAllowed("ROLE_ADMINISTRATION")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<CreateExerciseResponse> createExercise(@RequestPart("exercise") @Valid CreateExerciseRequest request, @RequestPart("file") MultipartFile file)
    {
        String imageUrl = null;
        imageUrl = cloudinary.uploadImage(file);
        Exercise exerciseRequestConverted = ExerciseConverter.convert(request);
        exerciseRequestConverted.setImageUrl(imageUrl);
        CreateExerciseResponse createExerciseResponse = new CreateExerciseResponse();
        createExerciseResponse.setId(exerciseManager.createExercise(exerciseRequestConverted));
        return ResponseEntity.status(HttpStatus.CREATED).body(createExerciseResponse);
    }

@IsAuthenticated
@RolesAllowed("ROLE_ADMINISTRATION")
@PutMapping("/{exerciseId}")
public ResponseEntity<UpdateExerciseResponse> updateExercise(@PathVariable int exerciseId, @RequestPart("exercise") @Valid UpdateExerciseRequest request, @RequestPart(value = "file", required = false) MultipartFile file)
{
    String imageUrl = null;
    Exercise ex =  exerciseManager.getExercise(exerciseId);
    if (file != null && !file.isEmpty()) {
        imageUrl = cloudinary.uploadImage(file);
    } else {
        imageUrl = ex.getImageUrl();
    }
    UpdateExerciseRequest test = new UpdateExerciseRequest();
    test.setId(Long.valueOf(exerciseId));
    test.setName(request.getName());
    test.setDescription(request.getDescription());
    test.setImageUrl(imageUrl);
    exerciseManager.updateExercise(test);
    return ResponseEntity.status(HttpStatus.OK).body(new UpdateExerciseResponse(Long.valueOf(exerciseId)));

}



}
