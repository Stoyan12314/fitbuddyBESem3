package org.example.buisness.impl;


import org.example.buisness.exceptions.UserNotFoundException;
import org.example.controller.converters.UserConverter;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserManagerImplTest {

    @Test
    @DisplayName("Should return all users")
    void getAllUsersTest() {
        UserRepository userRepository = mock(UserRepository.class);
        UserManagerImpl userManager = new UserManagerImpl(userRepository);

        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();

        List<UserEntity> userEntitiesList = Arrays.asList(userEntity1, userEntity2);
        Page<UserEntity> userEntities = new PageImpl<>(userEntitiesList, pageable, userEntitiesList.size());

        when(userRepository.getAllUsers(pageable)).thenReturn(userEntities);

        Page<User> result = userManager.getAllUsers(page, size);

        assertNotNull(result);
        assertEquals(userEntities.getTotalElements(), result.getTotalElements());
    }

    @Test
    @DisplayName("Should return users by email")
    void getUsersByEmailTest() {
        UserRepository userRepository = mock(UserRepository.class);
        UserManagerImpl userManager = new UserManagerImpl(userRepository);

        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        String email = "test@test.com";

        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();

        List<UserEntity> userEntitiesList = Arrays.asList(userEntity1, userEntity2);
        Page<UserEntity> userEntities = new PageImpl<>(userEntitiesList, pageable, userEntitiesList.size());

        when(userRepository.getUsersByEmail(email, pageable)).thenReturn(userEntities);

        Page<User> result = userManager.getUsersByEmail(email, page, size);

        assertNotNull(result);
        assertEquals(userEntities.getTotalElements(), result.getTotalElements());
    }

    @Test
    @DisplayName("Should return a user by ID")
    void getUserTest() {
        UserRepository userRepository = mock(UserRepository.class);
        UserManagerImpl userManager = new UserManagerImpl(userRepository);

        long id = 1L;

        UserEntity userEntity = new UserEntity();

        when(userRepository.findUserById(id)).thenReturn(Optional.of(userEntity));

        Optional<User> result = userManager.getUser(id);

        assertTrue(result.isPresent());
        assertEquals(UserConverter.convert(userEntity), result.get());
    }

    @Test
    @DisplayName("Should return empty Optional when user does not exist")
    void getUserNotFoundTest() {
        UserRepository userRepository = mock(UserRepository.class);
        UserManagerImpl userManager = new UserManagerImpl(userRepository);

        long id = 1L;

        when(userRepository.findUserById(id)).thenReturn(Optional.empty());

        Optional<User> result = userManager.getUser(id);

        assertTrue(result.isEmpty());
    }
}