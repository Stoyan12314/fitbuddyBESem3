package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.RegisterManager;
import org.example.buisness.RequestManager;
import org.example.controller.RequestsResponds.*;
import org.example.domain.Exercise;
import org.example.domain.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/request")
@AllArgsConstructor
public class RequestController {
    private final RequestManager requestManager;

    @GetMapping("/{userId}")
    public ResponseEntity<GetRequestsResponse> getExercise(@PathVariable int userId)
    {
        System.out.println("User id for requesting exercises: " + userId);

        List<Request> request = requestManager.getUserExercises(userId);
        System.out.println("Fetched " + request.size() + " requests");
        if (request.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            GetRequestsResponse response = new GetRequestsResponse(request);

            return ResponseEntity.ok(response);
        }
    }
    @DeleteMapping("/{id}")
    public void DeleteExercise(@PathVariable int exerciseId)
    {
        requestManager.deleteExercise(exerciseId);
    }
    @PostMapping
    public ResponseEntity<RequestResponse> createRequest(@RequestBody @Valid CreateRequestRequest requestRequest)
    {
        RequestResponse response  =  requestManager.assignExercise(requestRequest);
        return ResponseEntity.ok(response);
    }




}
