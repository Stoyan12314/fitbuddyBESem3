package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.RequestManager;
import org.example.controller.dto.*;
import org.example.domain.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/request")
@AllArgsConstructor
public class RequestController {
    private final RequestManager requestManager;

    @GetMapping("/{userId}")
    public ResponseEntity<GetRequestsResponse> getExercise(@PathVariable int userId)
    {
        List<Request> request = requestManager.getUserExercises(userId);
        if (request.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            GetRequestsResponse response = new GetRequestsResponse(request);

            return ResponseEntity.ok(response);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable int id)
    {
        requestManager.deleteExercise(id);
    }
    @PostMapping
    public ResponseEntity<RequestResponse> createRequest(@RequestBody @Valid CreateRequestRequest requestRequest)
    {
        RequestResponse response  =  requestManager.assignExercise(requestRequest);
        return ResponseEntity.ok(response);
    }




}
