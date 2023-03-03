package org.example.persistence;

import org.example.persistence.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    List<UserEntity> findAll();

    void deleteById(long exerciseId);

    UserEntity saveUser(UserEntity user);
}
