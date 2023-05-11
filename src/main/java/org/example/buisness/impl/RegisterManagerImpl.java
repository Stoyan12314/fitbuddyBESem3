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
<<<<<<< HEAD
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> development
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterManagerImpl implements RegisterManager {


<<<<<<< HEAD
    private final PasswordEncoder passwordEncoder;
=======
>>>>>>> development

    private final UserRepository repo;


    @Override
    public RegisterResponse createUser(RegisterRequest request) {
        Long id = saveNewUser(request).getId();

        return RegisterResponse.builder()
                .id(id)
                .build();

    }
<<<<<<< HEAD

    private UserEntity saveNewUser(RegisterRequest request) {

        System.out.println("RegisterRequest: " + request);

        System.out.println("request: " + request.getPassword());

        UserEntity newUser = UserEntity.builder()

                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
=======
//    @Override
//    public CreateUserResponse createUser(RegisterRequest request)
//    {
//
//
//
//        saveNewUser(request);
//
//        return CreateUserResponse.builder()
//                .status("Done")
//                .build();
//
//
//    }
    private UserEntity saveNewUser(RegisterRequest request) {

        UserEntity newUser = UserEntity.builder()

                .email(request.getEmail())
                .password(request.getPassword())
>>>>>>> development
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.valueOf(request.getRole()))
                .build();

        return repo.saveUser( UserConverter.convert(newUser));
    }



}
