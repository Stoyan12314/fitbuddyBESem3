package org.example.persistence;

import org.example.persistence.entity.UserEntity;
<<<<<<< HEAD
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
@Configuration
=======
import org.springframework.data.jpa.repository.JpaRepository;

>>>>>>> development
public interface JPAUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstByEmail(String email);
}
