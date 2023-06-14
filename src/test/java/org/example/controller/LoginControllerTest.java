package org.example.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.buisness.LoginManager;
import org.example.controller.dto.LoginRequest;
import org.example.controller.dto.LoginResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginManager loginManager;

    @Test
    public void testLoginSuccess() throws Exception {
        LoginRequest loginRequest = createLoginRequest();
        LoginResponse expectedResponse = createLoginResponse();

        when(loginManager.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonString(loginRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(asJsonString(expectedResponse)));

        verify(loginManager, times(1)).login(any(LoginRequest.class));
    }

    @Test
    public void testLoginFailure() throws Exception {
        LoginRequest loginRequest = createLoginRequest();

        when(loginManager.login(any(LoginRequest.class))).thenReturn(null);

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonString(loginRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(loginManager, times(1)).login(any(LoginRequest.class));
    }

    private LoginRequest createLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("testUser");
        loginRequest.setPassword("testPassword");
        return loginRequest;
    }

    private LoginResponse createLoginResponse() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken("testToken");

        return loginResponse;
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

