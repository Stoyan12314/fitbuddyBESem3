package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.persistence.JPAUserExerciseRepository;
import org.example.persistence.UserExerciseRepo;
import org.example.persistence.entity.UserEntity;
import org.example.persistence.entity.UserExerciseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserExerciseRepositoryImpl implements UserExerciseRepo {
    private final JPAUserExerciseRepository jpaUserExerciseRepository;

    @Override
    public Optional<UserExerciseEntity> findExerciseById(long exerciseId) {
        return jpaUserExerciseRepository.findById(exerciseId);
    }

    @Override
    public void delete(UserExerciseEntity userExerciseEntity) {
        jpaUserExerciseRepository.delete(userExerciseEntity);
    }
    @Override
    public List<UserExerciseEntity> getUserExercises(long userId, LocalDate date) {
        return jpaUserExerciseRepository.findByUserIdAndDate(userId, date);
    }


    @Override
    public void save(UserExerciseEntity userExerciseEntity) {
        jpaUserExerciseRepository.save(userExerciseEntity);
    }

    @Override
    public List<UserExerciseEntity> getAllUserExercises(long userId) {
        return jpaUserExerciseRepository.findAllByUserId(userId);
    }
}