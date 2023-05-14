package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.persistence.JPARequestRepository;
import org.example.persistence.RequestRepository;
import org.example.persistence.entity.ExerciseEntity;
import org.example.persistence.entity.RequestEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RequestRepositoryImpl implements RequestRepository {
    private final JPARequestRepository jpaRepo;
    public RequestEntity assignExercise(RequestEntity requestEntity) {
        return jpaRepo.save(requestEntity);
    }

    @Override
    public Long createRequest(long userId, long exerciseId) {
        return null;
    }

    @Override
    public List<RequestEntity> getUserExercises(long userId) {
        return jpaRepo.getUserExercises(userId);
    }

    @Override
    public void deleteExercise(long exerciseId) {
        jpaRepo.deleteExercise(exerciseId);
    }


}
