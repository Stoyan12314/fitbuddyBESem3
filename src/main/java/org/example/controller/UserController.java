package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.UserManager;
import org.example.controller.RequestsResponds.GetUserResponse;
import org.example.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserManager userManager;


    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> getUser( @Valid @PathVariable Long id)
    {

        Optional<User> user = userManager.getUser(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        GetUserResponse response = GetUserResponse.builder()
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .email(user.get().getEmail())
                .password(user.get().getPassword())
                .build();
        return ResponseEntity.ok(response);

    }




}
