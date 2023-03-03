package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.UserManager;
import org.example.controller.RequestsResponds.CreateUserRequest;
import org.example.controller.RequestsResponds.CreateUserResponse;
import org.example.controller.RequestsResponds.GetUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserManager userManager;


    @GetMapping
    public ResponseEntity<GetUserResponse> getUsers()
    {
        return ResponseEntity.ok(userManager.getUsers());
    }


    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId)
    {
        userManager.deleteById(userId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request)
    {
        CreateUserResponse response = userManager.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
