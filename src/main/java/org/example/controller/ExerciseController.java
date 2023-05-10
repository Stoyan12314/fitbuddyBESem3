package org.example.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.example.buisness.Exceptions.CreateExerciseException;
import org.example.buisness.Exceptions.UpdateExerciseException;
import org.example.buisness.ExerciseManager;
import org.example.controller.RequestsResponds.*;
import org.example.controller.converters.ExerciseConverter;
import org.example.domain.Exercise;
import org.example.security.isauthenticated.IsAuthenticated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/exercises")
@AllArgsConstructor
public class ExerciseController {
    private final ExerciseManager exerciseManager;
    private final Cloudinary cloudinary;

    @GetMapping("/{exerciseId}")
    public ResponseEntity<GetExerciseResponse> getExercise(@PathVariable int exerciseId)
    {
        Optional<Exercise> exercise = exerciseManager.getExercise(exerciseId);
        if (exercise.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            GetExerciseResponse response = new GetExerciseResponse(exercise);

            return ResponseEntity.ok(response);
        }
    }


    @GetMapping
    public ResponseEntity<GetExercisesResponse> getExercises()
    {
        List<Exercise> exercises= exerciseManager.getExercises();
        if (exercises.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            GetExercisesResponse response = new GetExercisesResponse(exercises);
            return ResponseEntity.ok(response);
        }
    }

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
        // Upload the image file to Cloudinary
        String imageUrl = null;
        if (file != null && !file.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                imageUrl = (String) uploadResult.get("url");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
//move it to a buisness logic- manager class
        // Set the imageUrl to the exerciseRequestConverted object
        Exercise exerciseRequestConverted = ExerciseConverter.convert(request);
        exerciseRequestConverted.setImageUrl(imageUrl);

        CreateExerciseResponse createExerciseResponse = new CreateExerciseResponse();
        try {
            createExerciseResponse.setId(exerciseManager.createExercise(exerciseRequestConverted));
        } catch (CreateExerciseException e) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createExerciseResponse);

    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateExercise(@PathVariable("id") final Long id,
                                               @RequestBody @Valid UpdateExerciseRequest request) {
        request.setId(id);

        Exercise exercise = ExerciseConverter.convert(request);
        try {
            exerciseManager.updateExercise(exercise);
        }
        catch (UpdateExerciseException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
