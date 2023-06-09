package org.example.buisness.impl;


import org.example.domain.Role;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserManagerImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserManagerImpl userManager;

    @Test
    @DisplayName("Should return an empty optional when the user with the given id does not exist")
    void getUserWhenUserDoesNotExist() {
        Long userId = 1L;
        when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

        Optional<User> user = userManager.getUser(userId);

        assertThat(user).isEmpty();
        verify(userRepository, times(1)).findUserById(userId);
    }

    @Test
    @DisplayName("Should return the user when the user with the given id exists")
    void getUserWhenUserExists() {
        Long userId = 1L;
        UserEntity userEntity =
                UserEntity.builder()
                        .id(userId)
                        .email("john.doe@example.com")
                        .password("password")
                        .firstName("John")
                        .lastName("Doe")
                        .role(Role.CUSTOMER)
                        .build();
        User expectedUser =
                User.builder()
                        .id(userId)
                        .email("john.doe@example.com")
                        .password("password")
                        .firstName("John")
                        .lastName("Doe")
                        .role(Role.CUSTOMER)
                        .build();
        when(userRepository.findUserById(userId)).thenReturn(Optional.of(userEntity));

        Optional<User> actualUser = userManager.getUser(userId);

        assertTrue(actualUser.isPresent());
        assertThat(actualUser.get()).isEqualTo(expectedUser);
        verify(userRepository, times(1)).findUserById(userId);
    }
}