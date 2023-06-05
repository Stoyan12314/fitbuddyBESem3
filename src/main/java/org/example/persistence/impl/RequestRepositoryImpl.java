package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.domain.Exercise;
import org.example.persistence.JPARequestRepository;
import org.example.persistence.RequestRepository;
import org.example.persistence.entity.RequestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class RequestRepositoryImpl implements RequestRepository {
    private final JPARequestRepository jpaRepo;


    @Override
    public RequestEntity assignExercise(RequestEntity requestEntity) {
        return jpaRepo.save(requestEntity);
    }

    @Override
    public Long createRequest(long userId, long exerciseId) {
        return null;
    }

    @Override
    public List<RequestEntity> getUserExercises(long userId, long exerciseId) {

        return jpaRepo.getUserExercises(userId, exerciseId);
    }

    @Override
    @Transactional
    public void deleteExercise(long exerciseId) {
        jpaRepo.deleteById(exerciseId);
    }


}
