package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.UserManager;
import org.example.controller.converters.UserConverter;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManager {

    private final UserRepository repo;

    @Override
    public Optional<User> getUser(Long id) {
        return repo.findUserById(id).map(UserConverter::convert);
    }
}
