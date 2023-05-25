
package org.example.persistence.impl;

import lombok.AllArgsConstructor;

import org.example.persistence.JPAUserRepository;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;


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

                return jpaRepo.findById(id);

    }

    @Override
    public UserEntity saveUser(UserEntity user) {

        jpaRepo.save(user);
        return user;
    }
}


