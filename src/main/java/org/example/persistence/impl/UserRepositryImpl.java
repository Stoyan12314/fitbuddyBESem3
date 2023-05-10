
package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.controller.converters.UserConverter;
import org.example.domain.User;
import org.example.persistence.JPAExerciseRepository;
import org.example.persistence.JPAUserRepository;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositryImpl implements UserRepository {
    private final JPAUserRepository jpaRepo;


    @Override
    public UserEntity findUserByEmail(String email) {
        UserEntity user = jpaRepo.findFirstByEmail(email);
        if(user != null) {
            return user;
        }
        return null;

    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        Optional<UserEntity> entity = jpaRepo.findById(id);
        return entity;
    }

    @Override
    public UserEntity saveUser(User user) {
        System.out.println("User: " + user);
        UserEntity userEntity = UserConverter.convertToEntity(user);

        System.out.println("userEntity: " + userEntity);
        jpaRepo.save(userEntity);
        return userEntity;
    }
}

