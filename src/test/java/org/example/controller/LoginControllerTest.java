package org.example.controller;

import org.example.buisness.LoginManager;
import org.example.buisness.impl.LoginManagerImpl;
import org.example.controller.RequestsResponds.LoginRequest;
import org.example.controller.RequestsResponds.LoginResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private RequestManager requestManager;

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginManager loginManager;


    @Test
    void loginTest_shouldReturnTrue() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("testUser");
        loginRequest.setPassword("testPassword");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setAccessToken("testToken");

        when(loginManager.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<LoginResponse> response = loginController.login(loginRequest);

        // Assert
        assertEquals(expectedResponse, response.getBody());
        verify(loginManager, times(1)).login(loginRequest);
    }
  
    @Test
    void login_userNotFound() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("testUser");
        loginRequest.setPassword("testPassword");

        when(loginManager.login(any(LoginRequest.class))).thenReturn(null);

        // Act
        ResponseEntity<LoginResponse> response = loginController.login(loginRequest);

        // Assert
        assertNull(response.getBody());
        verify(loginManager, times(1)).login(loginRequest);
    }

    @Test
    void login_invalidRequest() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(null);
        loginRequest.setPassword("testPassword");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setAccessToken("testToken");

        when(loginManager.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<LoginResponse> response = loginController.login(loginRequest);

        // Assert
        assertNotEquals(expectedResponse, response.getBody());
        verify(loginManager, times(0)).login(loginRequest);
    }


}