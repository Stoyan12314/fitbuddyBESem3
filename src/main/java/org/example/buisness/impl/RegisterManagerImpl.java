package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.exceptions.EmailExistException;
import org.example.buisness.RegisterManager;
import org.example.buisness.exceptions.InvalidCredentialsException;
import org.example.controller.dto.RegisterRequest;
import org.example.controller.dto.RegisterResponse;
import org.example.domain.Role;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterManagerImpl implements RegisterManager {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repo;

    @Override
    public RegisterResponse createUser(RegisterRequest request) {
        Long id = saveNewUser(request).getId();

        return RegisterResponse.builder()
                .id(id)
                .build();
    }

    private UserEntity saveNewUser(RegisterRequest request) {


        UserEntity existingUser = repo.findUserByEmail(request.getEmail());


        String email = request.getEmail();
        if (!isValidEmail(email)) {
            throw new InvalidCredentialsException();
        }
        if (existingUser != null) {
            throw new EmailExistException();
        }


        UserEntity newUser = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.valueOf(request.getRole()))
                .build();

        return repo.saveUser(newUser);
    }


    private boolean isValidEmail(String email) {
        if(!email.contains("@"))
        {
            return false;
        }
       return true;
    }
}
