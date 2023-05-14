package org.example.persistence;

import org.example.domain.Request;
import org.example.persistence.entity.ExerciseEntity;
import org.example.persistence.entity.RequestEntity;

import java.util.List;

public interface RequestRepository {
    Long createRequest(long userId, long exerciseId);
    RequestEntity assignExercise(RequestEntity requestEntity);

    List<RequestEntity> getUserExercises(long userId);
    public void deleteExercise(long exerciseId);

}
