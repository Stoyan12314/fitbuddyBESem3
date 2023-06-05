package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.UserExerciseManager;
import org.example.controller.dto.AssignExerciseRequest;
import org.example.controller.dto.GetUserExercisesResponse;
import org.example.domain.UserExercise;
import org.example.security.isauthenticated.IsAuthenticated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/userExercises")
@AllArgsConstructor
public class UserExerciseController {
    private final UserExerciseManager userExerciseManager;

    @IsAuthenticated
    @CrossOrigin("http://localhost:3000")

    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_TRAINER"})
    @GetMapping("/{userId}")
    public ResponseEntity<GetUserExercisesResponse> getUserExercises(@PathVariable long userId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        List<UserExercise> userExercises = userExerciseManager.getUserExercises(userId,localDate);
        if (userExercises.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            GetUserExercisesResponse response = new GetUserExercisesResponse(userExercises);
            return ResponseEntity.ok(response);
        }
    }


    @IsAuthenticated
    @CrossOrigin("http://localhost:3000")

    @RolesAllowed("ROLE_TRAINER")
    @DeleteMapping("{exerciseId}")
    public ResponseEntity<Void> unassignExerciseFromUser(@PathVariable long exerciseId) {
        userExerciseManager.unassignExerciseFromUser(exerciseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @IsAuthenticated
    @CrossOrigin("http://localhost:3000")

    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_TRAINER"})
    @PostMapping
    public ResponseEntity<Void> assignExerciseToUser(@RequestBody @Valid AssignExerciseRequest request) {


        userExerciseManager.assignExerciseToUser(request.getUserId(), request.getExerciseId(), request.getDate());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<GetUserExercisesResponse> getAllUserExercises(@PathVariable long userId) {

        List<UserExercise> userExercises = userExerciseManager.getAllUserExercises(userId);
        if (userExercises.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            GetUserExercisesResponse response = new GetUserExercisesResponse(userExercises);
            return ResponseEntity.ok(response);
        }
    }


}
