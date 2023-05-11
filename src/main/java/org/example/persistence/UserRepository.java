package org.example.persistence;

import org.example.domain.User;
import org.example.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
<<<<<<< HEAD
    UserEntity findUserByEmail(String email);

    Optional<UserEntity> findUserById(Long id);
=======
    User findUserByEmail(String email);

    Optional<User> findUserById(Long id);
>>>>>>> development

    UserEntity saveUser(User user);
}
