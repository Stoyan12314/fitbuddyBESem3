package org.example.buisness.impl;

import lombok.AllArgsConstructor;
<<<<<<< HEAD
import org.example.buisness.AccessTokenEncoder;
=======
>>>>>>> development
import org.example.buisness.Exceptions.InvalidCredentialsException;
import org.example.buisness.LoginManager;
import org.example.controller.RequestsResponds.LoginRequest;
import org.example.controller.RequestsResponds.LoginResponse;
<<<<<<< HEAD
import org.example.controller.UserController;
import org.example.controller.converters.UserConverter;
import org.example.domain.AccessToken;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

=======
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

>>>>>>> development
@Service
@AllArgsConstructor
public class LoginManagerImpl implements LoginManager {

    private final UserRepository userRepository;
<<<<<<< HEAD
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {

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


=======
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());

        LoginResponse request = new LoginResponse();
        request.setStatus("logged!");
        return request;

    }
>>>>>>> development
}
