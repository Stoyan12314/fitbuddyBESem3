package org.example.persistence;

import org.example.persistence.entity.UserEntity;
import org.example.persistence.entity.UserExerciseEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
@Configuration

public interface JPAUserExerciseRepository extends JpaRepository<UserExerciseEntity, Long> {
    @Query("select u from UserExerciseEntity u where u.user.id = :userId and u.date = :date")
    List<UserExerciseEntity> findByUserIdAndDate(@Param("userId") long userId, @Param("date") LocalDate date);
    @Query("select u from UserExerciseEntity u where u.user.id = :userId")
    List<UserExerciseEntity> findAllByUserId(@Param("userId") long userId);
}
