package org.example.controller;

import org.example.buisness.RegisterManager;
import org.example.controller.RequestsResponds.RegisterRequest;
import org.example.controller.RequestsResponds.RegisterResponse;
import org.example.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTest {

    @Mock
    private RegisterManager registerManager;

    @InjectMocks
    private RegisterController registerController;

    @Test
    void registerUser_shouldReturnCreatedStatus() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("testUser");
        registerRequest.setPassword("testPassword");

        RegisterResponse registerResponse = new RegisterResponse();
                registerResponse.setId(1L);


        when(registerManager.createUser(any(RegisterRequest.class))).thenReturn(registerResponse);

        // Act
        ResponseEntity<RegisterResponse> response = registerController.register(registerRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        verify(registerManager, times(1)).createUser(registerRequest);
    }


    @Test
    void registerUser_invalidRequest() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(null);
        registerRequest.setPassword("testPassword");

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(1L);


        when(registerManager.createUser(any(RegisterRequest.class))).thenReturn(registerResponse);

        // Act
        ResponseEntity<RegisterResponse> response = registerController.register(registerRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        verify(registerManager, times(1)).createUser(any(RegisterRequest.class));
    }
}