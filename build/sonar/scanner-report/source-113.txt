package org.example.buisness.impl;

import org.example.buisness.exceptions.EmailExistException;
import org.example.buisness.exceptions.InvalidCredentialsException;
import org.example.controller.dto.RegisterRequest;
import org.example.controller.dto.RegisterResponse;
import org.example.domain.Role;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterManagerImplTest {

    @Test
    @DisplayName("Should throw an EmailExistException when the email is already taken")
    void createUserWhenEmailIsAlreadyTakenThenThrowEmailExistException() {
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        RegisterManagerImpl registerManager =
                new RegisterManagerImpl(passwordEncoder, userRepository);

        RegisterRequest registerRequest =
                RegisterRequest.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john.doe@example.com")
                        .password("password")
                        .role(Role.CUSTOMER.name())
                        .build();

        when(userRepository.findUserByEmail(registerRequest.getEmail()))
                .thenReturn(new UserEntity());

        assertThrows(
                EmailExistException.class,
                () -> registerManager.createUser(registerRequest),
                "Should throw an EmailExistException when the email is already taken");
    }

    @Test
    @DisplayName("Should create a new user when the email is not taken")
    void createUserWhenEmailIsNotTaken() {
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        UserRepository userRepository = mock(UserRepository.class);
        RegisterManagerImpl registerManager =
                new RegisterManagerImpl(passwordEncoder, userRepository);

        RegisterRequest registerRequest =
                RegisterRequest.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john.doe@example.com")
                        .password("password")
                        .role(Role.CUSTOMER.toString())
                        .build();

        UserEntity userEntity =
                UserEntity.builder()
                        .id(1L)
                        .firstName(registerRequest.getFirstName())
                        .lastName(registerRequest.getLastName())
                        .email(registerRequest.getEmail())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .role(Role.CUSTOMER)
                        .build();

        when(userRepository.findUserByEmail(registerRequest.getEmail())).thenReturn(null);
        when(userRepository.saveUser(any(UserEntity.class))).thenReturn(userEntity);

        RegisterResponse registerResponse = registerManager.createUser(registerRequest);

        assertNotNull(registerResponse);
        assertEquals(userEntity.getId(), registerResponse.getId());
        verify(userRepository, times(1)).findUserByEmail(registerRequest.getEmail());
        verify(userRepository, times(1)).saveUser(any(UserEntity.class));
    }


    @Test
    @DisplayName("Should throw an InvalidCredentialsException when the email is invalid")
    void createUserWhenEmailIsInvalidThenThrowInvalidCredentialsException() {
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        RegisterManagerImpl registerManager =
                new RegisterManagerImpl(passwordEncoder, userRepository);

        RegisterRequest registerRequest =
                RegisterRequest.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("invalid-email")
                        .password("password")
                        .role(Role.CUSTOMER.name())
                        .build();

        assertThrows(
                InvalidCredentialsException.class,
                () -> registerManager.createUser(registerRequest),
                "Should throw an InvalidCredentialsException when the email is invalid");
    }


}