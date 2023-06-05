package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.UserManager;
import org.example.controller.converters.MessageConverter;
import org.example.controller.converters.UserConverter;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManager {

    private final UserRepository repo;

    @Override
    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> userEntities = repo.getAllUsers(pageable);
        List<User> users = userEntities.stream()
                .map(UserConverter::convert)
                .collect(Collectors.toList());
        return new PageImpl<>(users, pageable, userEntities.getTotalElements());
    }

    @Override
    public Page<User> getUsersByEmail(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> userEntities = repo.getUsersByEmail(email, pageable);
        List<User> users = userEntities.stream()
                .map(UserConverter::convert)
                .collect(Collectors.toList());
        return new PageImpl<>(users, pageable, userEntities.getTotalElements());
    }


    @Override
    public Optional<User> getUser(Long id) {
        return repo.findUserById(id).map(UserConverter::convert);
    }
}
