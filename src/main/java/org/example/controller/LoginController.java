package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.LoginManager;
import org.example.controller.RequestsResponds.LoginRequest;
import org.example.controller.RequestsResponds.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
<<<<<<< HEAD
=======
@RequestMapping("/login")
>>>>>>> development
@AllArgsConstructor
public class LoginController {

    private final LoginManager loginManager;

<<<<<<< HEAD
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest)
    {


        LoginResponse loginResponse = loginManager.login(loginRequest);
        System.out.println("LoginRequest: " + loginResponse.getAccessToken());
=======
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest)
    {
        LoginResponse loginResponse = loginManager.login(loginRequest);
>>>>>>> development
        return ResponseEntity.ok(loginResponse);
    }

}
