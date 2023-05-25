package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.RegisterManager;
import org.example.controller.dto.RegisterRequest;
import org.example.controller.dto.RegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final RegisterManager registerManager;


@PostMapping
public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {

        RegisterResponse registerResponse = RegisterResponse.builder()
                .id(registerManager.createUser(registerRequest).getId())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);

    }
}


