package org.example.controller;

import org.example.buisness.UserManager;
import org.example.domain.Role;
import org.example.controller.RequestsResponds.CreateUserRequest;
import org.example.controller.RequestsResponds.CreateUserResponse;
import org.example.controller.RequestsResponds.GetUserResponse;
import org.example.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserManager userManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        // Create mock response
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "john@example.com", "password", "John", "Doe", Role.ADMINISTRATION));
        users.add(new User(2L, "jane@example.com", "password", "Jane", "Doe", Role.CUSTOMER));
        GetUserResponse expectedResponse = new GetUserResponse(users);

        // Mock userManager's getUsers() method
        when(userManager.getUsers()).thenReturn(expectedResponse);

        // Call controller's getUsers() method
        ResponseEntity<GetUserResponse> actualResponse = userController.getUsers();

        // Verify response matches expected response
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    void testDeleteUser() {
        int userId = 1;

        // Call controller's deleteUser() method
        ResponseEntity<Void> actualResponse = userController.deleteUser(userId);

        // Verify userManager's deleteById() method is called with the correct argument
        verify(userManager).deleteById(userId);

        // Verify response is a no content response
        assertEquals(HttpStatus.NO_CONTENT, actualResponse.getStatusCode());
        assertNull(actualResponse.getBody());
    }

    @Test
    void testCreateUser() {
        // Create mock request
        CreateUserRequest request = new CreateUserRequest(1L,"john@example.com", "password", "John", "Doe", "ADMIN");

        // Create mock response
        CreateUserResponse expectedResponse = CreateUserResponse.builder()
                .status("Done")
                .build();

        // Mock userManager's createUser() method
        when(userManager.createUser(request)).thenReturn(expectedResponse);

        // Call controller's createUser() method
        ResponseEntity<CreateUserResponse> actualResponse = userController.createUser(request);

        // Verify response matches expected response
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }
}