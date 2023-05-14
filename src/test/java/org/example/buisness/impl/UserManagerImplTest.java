package org.example.buisness.impl;

import org.example.controller.converters.UserConverter;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class UserManagerImplTest {

    @Test
    void getUserByIdFound() {
        // Arrange
        UserRepository userRepositoryMock = mock(UserRepository.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        Optional<UserEntity> expectedResult = Optional.of(userEntity);

        when(userRepositoryMock.findUserById(1L)).thenReturn(expectedResult);

        UserManagerImpl userManager = new UserManagerImpl(userRepositoryMock);

        // Act
        Optional<User> actualResult = userManager.getUser(1L);

        // Assert
        assertEquals(expectedResult.map(UserConverter::convert), actualResult);
        verify(userRepositoryMock).findUserById(1L);
    }

    @Test
    void getUserByIdNotFound() {
        // Arrange
        UserRepository userRepositoryMock = mock(UserRepository.class);

        when(userRepositoryMock.findUserById(2L)).thenReturn(Optional.empty());

        UserManagerImpl userManager = new UserManagerImpl(userRepositoryMock);

        // Act
        Optional<User> actualResult = userManager.getUser(2L);

        // Assert
        assertEquals(Optional.empty(), actualResult);
        verify(userRepositoryMock).findUserById(2L);
    }
}