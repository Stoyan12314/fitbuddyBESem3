package org.example.persistence;

import org.example.persistence.entity.UserEntity;
import java.util.Optional;

public interface UserRepository {
    UserEntity findUserByEmail(String email);
    Optional<UserEntity> findUserById(Long id);
    UserEntity saveUser(UserEntity user);
}
