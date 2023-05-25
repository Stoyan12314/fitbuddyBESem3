
package org.example.buisness.impl;

import org.example.buisness.LoginManager;
import org.example.persistence.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class LoginManagerImplTest {

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoderMock;

    @Mock
    private LoginManager loginManager;

//    @Test
//    void login_shouldReturnUserAndRole() {
//        // Arrange
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("testUser@test.com");
//        loginRequest.setPassword("testPassword");
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail("testUser@test.com");
//        userEntity.setPassword("testEncodedPassword");
//        userEntity.setRole(Role.CUSTOMER);
//        userEntity.setId(1L);
//
//        when(userRepositoryMock.findUserByEmail(eq(loginRequest.getEmail()))).thenReturn(userEntity);
//        when(passwordEncoderMock.matches(eq(loginRequest.getPassword()), eq(userEntity.getPassword()))).thenReturn(true);
//        userRepositoryMock.saveUser(userEntity);
//        // Act
//        LoginResponse actualResponse = loginManager.login(loginRequest);
//
//        // Assert
//        assertNotNull(actualResponse);
//        assertEquals(Collections.singletonList(Role.CUSTOMER.toString()), actualResponse.getRoles());
//
//        verify(userRepositoryMock).findUserByEmail(loginRequest.getEmail());
//        verify(passwordEncoderMock).matches(loginRequest.getPassword(), userEntity.getPassword());
//    }

}

