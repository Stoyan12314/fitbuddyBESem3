
package org.example.persistence.impl;

import lombok.AllArgsConstructor;

import org.example.persistence.JPAUserRepository;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositryImpl implements UserRepository {
    private final JPAUserRepository jpaRepo;


    @Override
    public Page<UserEntity> getAllUsers(Pageable pageable) {
        return jpaRepo.getAllUsers(pageable);
    }

    @Override
    public Page<UserEntity> getUsersByEmail(String email, Pageable pageable) {
        return jpaRepo.findByEmailContainingIgnoreCase(email, pageable);
    }

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


