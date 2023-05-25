package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.AccessTokenEncoder;
import org.example.buisness.exceptions.InvalidCredentialsException;
import org.example.buisness.LoginManager;
import org.example.buisness.exceptions.InvalidLoginRequestException;
import org.example.controller.dto.LoginRequest;
import org.example.controller.dto.LoginResponse;
import org.example.controller.converters.UserConverter;
import org.example.domain.AccessToken;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class LoginManagerImpl implements LoginManager {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        if(loginRequest == null)
        {
            throw new InvalidLoginRequestException();
        }

        UserEntity user = userRepository.findUserByEmail(loginRequest.getEmail());
        if(user == null)
        {
            throw new InvalidCredentialsException();
        }

        if(!matchesPassword(loginRequest.getPassword(), user.getPassword()))
        {
            throw new InvalidCredentialsException();
        }
        String accessToken = generateAccessToken(UserConverter.convert(user));
        List<String> roles = new ArrayList<>();
        roles.add(user.getRole().toString());
        return LoginResponse.builder().accessToken(accessToken)
                .roles(roles)
                .userId(user.getId())
                .build();
    }
    private String generateAccessToken(User user) {

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole().toString());
        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getEmail())
                        .roles(roles)
                        .userId(user.getId())

                        .build());
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }



}
