package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.buisness.RegisterManager;
import org.example.controller.dto.RegisterRequest;
import org.example.controller.dto.RegisterResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterManager registerManager;
    @Test
    public void testRegisterFailure() throws Exception {
        RegisterRequest registerRequest = createRegisterRequest();
        RegisterResponse expectedResponse = createRegisterResponse();

        when(registerManager.createUser(any(RegisterRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/register")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonString(registerRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(asJsonString(expectedResponse)));

        verify(registerManager, times(1)).createUser(any(RegisterRequest.class));
    }
    @Test
    public void testRegisterSuccess() throws Exception {
        RegisterRequest registerRequest = createRegisterRequest();
        RegisterResponse expectedResponse = createRegisterResponse();

        when(registerManager.createUser(any(RegisterRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/register")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonString(registerRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(asJsonString(expectedResponse)));

        verify(registerManager, times(1)).createUser(any(RegisterRequest.class));
    }

    private RegisterRequest createRegisterRequest() {



        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("testUserFirst");
        registerRequest.setLastName("testUserLast");
        registerRequest.setEmail("testUser@gmail.com");
        registerRequest.setPassword("testPassword");
        registerRequest.setRole("CUSTOMER");
        return registerRequest;
    }

    private RegisterResponse createRegisterResponse() {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(1L);

        return registerResponse;
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

