package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.UserManager;
import org.example.controller.RequestsResponds.*;
import org.example.controller.converters.UserConverter;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.persistence.FakeUserRepositoryImpl;
import org.example.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManager {

    private final FakeUserRepositoryImpl repo;


    @Override
    public GetUserResponse getUsers() {
        List<UserEntity> results = repo.findAll();


        final GetUserResponse response = new GetUserResponse();
        List<User> users = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setUsers(users);


        return response;
    }






    @Override
    public void deleteById(long userId) {

        repo.deleteById(userId);

    }




    @Override
    public CreateUserResponse createUser(CreateUserRequest request)
    {



        saveNewUser(request);

        return CreateUserResponse.builder()
                .status("Done")
                .build();


    }
    private UserEntity saveNewUser(CreateUserRequest request) {

        UserEntity newExercise = UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.valueOf(request.getRole()))
                .build();
        return repo.saveUser(newExercise);
    }





}
