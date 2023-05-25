package org.example.controller;

import org.example.buisness.RegisterManager;
import org.example.controller.dto.RegisterRequest;
import org.example.controller.dto.RegisterResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class RegisterControllerTest {

    @Test
    @DisplayName("Should return a bad request status when the input is invalid")
    void registerWithInvalidInputThenReturnBadRequestStatus() {
        RegisterManager registerManager = mock(RegisterManager.class);

        RegisterController registerController = new RegisterController(registerManager);

        RegisterRequest registerRequest =
                RegisterRequest.builder()
                        .firstName("")
                        .lastName("")
                        .email("")
                        .password("")
                        .role(null)
                        .build();

        when(registerManager.createUser(registerRequest)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> registerController.register(registerRequest));

        verify(registerManager, times(1)).createUser(registerRequest);
    }
    @Test
    @DisplayName("Should register a new user and return the created user ID")
    void registerNewUserAndReturnCreatedUserId() {
        RegisterRequest registerRequest =
                RegisterRequest.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john.doe@example.com")
                        .password("password")
                        .role("USER")
                        .build();

        RegisterResponse expectedResponse = RegisterResponse.builder().id(1L).build();

        RegisterManager registerManager = mock(RegisterManager.class);
        when(registerManager.createUser(any(RegisterRequest.class))).thenReturn(expectedResponse);

        RegisterController registerController = new RegisterController(registerManager);

        ResponseEntity<?> responseEntity = registerController.register(registerRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(registerManager, times(1)).createUser(registerRequest);
    }
}

