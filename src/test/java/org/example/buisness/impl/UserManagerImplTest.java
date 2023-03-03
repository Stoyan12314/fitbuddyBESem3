package org.example.buisness.impl;

import org.example.controller.RequestsResponds.CreateUserRequest;
import org.example.controller.RequestsResponds.CreateUserResponse;
import org.example.controller.RequestsResponds.GetUserResponse;
import org.example.controller.converters.UserConverter;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.example.persistence.FakeUserRepositoryImpl;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserManagerImplTest {

    @InjectMocks
    private UserManagerImpl userManager;

    @Mock
    private FakeUserRepositoryImpl userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        // Create mock response
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add
                (
                 UserEntity.builder()
                         .id(1L)
                         .email("john@example.com")
                         .password("password")
                         .firstName("John")
                         .lastName("Doe")
                         .role(Role.ADMINISTRATION)
                         .build()
                );
        userEntities.add
                (
                        UserEntity.builder()
                                .id(2L)
                                .email("Jane@example.com")
                                .password("password")
                                .firstName("Jane")
                                .lastName("Doe")
                                .role(Role.CUSTOMER)
                                .build()
                );

        when(userRepo.findAll()).thenReturn(userEntities);

        final GetUserResponse expectedResponse = new GetUserResponse();
        List<User> users = userEntities
                .stream()
                .map(UserConverter::convert)
                .toList();
        expectedResponse.setUsers(users);

        // Call userManager's getUsers() method
        final GetUserResponse actualResponse = userManager.getUsers();

        // Verify response matches expected response
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testDeleteById() {

        // Create a new user entity and save it
        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("john@example.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .role(Role.ADMINISTRATION)
                .build();
        userRepo.saveUser(user);

        // Call userManager's deleteById() method to delete the user
        userManager.deleteById(1);

        // Verify that the user has been deleted
        assertFalse(userRepo.findAll().contains(user));
    }

    @Test
    void testCreateUser()
    {

    }
}