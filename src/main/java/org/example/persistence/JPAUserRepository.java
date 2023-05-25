package org.example.persistence;

import org.example.persistence.entity.UserEntity;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface JPAUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstByEmail(String email);
}
