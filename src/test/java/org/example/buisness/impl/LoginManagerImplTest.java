package org.example.buisness.impl;

import org.example.buisness.AccessTokenEncoder;
import org.example.buisness.LoginManager;
import org.example.controller.RequestsResponds.LoginRequest;
import org.example.controller.RequestsResponds.LoginResponse;
import org.example.controller.converters.UserConverter;
import org.example.domain.AccessToken;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.buisness.Exceptions.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginManagerImplTest {

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoderMock;

    @Mock
    private LoginManager loginManager;

    @Test
    void login_shouldReturnUserAndRole() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("testUser@test.com");
        loginRequest.setPassword("testPassword");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("testUser@test.com");
        userEntity.setPassword("testEncodedPassword");
        userEntity.setRole(Role.CUSTOMER);
        userEntity.setId(1L);

        when(userRepositoryMock.findUserByEmail(eq(loginRequest.getEmail()))).thenReturn(userEntity);
        when(passwordEncoderMock.matches(eq(loginRequest.getPassword()), eq(userEntity.getPassword()))).thenReturn(true);
        userRepositoryMock.saveUser(userEntity);
        // Act
        LoginResponse actualResponse = loginManager.login(loginRequest);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(Collections.singletonList(Role.CUSTOMER.toString()), actualResponse.getRoles());

        verify(userRepositoryMock).findUserByEmail(loginRequest.getEmail());
        verify(passwordEncoderMock).matches(loginRequest.getPassword(), userEntity.getPassword());
    }

}