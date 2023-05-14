package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.RegisterManager;
import org.example.controller.RequestsResponds.RegisterRequest;
import org.example.controller.RequestsResponds.RegisterResponse;
import org.example.controller.converters.UserConverter;
import org.example.domain.Role;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterManagerImpl implements RegisterManager {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repo;

    @Override
    public RegisterResponse createUser(RegisterRequest request) {
        Long id = saveNewUser(request).getId();

        return RegisterResponse.builder()
                .id(id)
                .build();
    }

    private UserEntity saveNewUser(RegisterRequest request) {
        System.out.println("RegisterRequest: " + request);
        System.out.println("request: " + request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.valueOf(request.getRole()))
                .build();

        return repo.saveUser(newUser);
    }
}
