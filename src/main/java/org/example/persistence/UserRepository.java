package org.example.persistence;

import org.example.domain.User;
import org.example.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserEntity findUserByEmail(String email);

    Optional<UserEntity> findUserById(Long id);

    UserEntity saveUser(User user);
}
