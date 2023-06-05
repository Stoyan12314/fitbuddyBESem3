package org.example.persistence;

import org.example.persistence.entity.UserEntity;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Configuration
public interface JPAUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstByEmail(String email);

    Page<UserEntity> findByEmailContainingIgnoreCase(String email, Pageable pageable);

    @Query("SELECT u FROM UserEntity u")
    Page<UserEntity> getAllUsers(Pageable pageable);
}
