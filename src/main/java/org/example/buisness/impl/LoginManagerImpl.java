package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.Exceptions.InvalidCredentialsException;
import org.example.buisness.LoginManager;
import org.example.controller.RequestsResponds.LoginRequest;
import org.example.controller.RequestsResponds.LoginResponse;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginManagerImpl implements LoginManager {

    private final UserRepository userRepository;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());

        LoginResponse request = new LoginResponse();
        request.setStatus("logged!");
        return request;

    }
}
