package org.example.persistence;
import org.example.persistence.entity.RequestEntity;

import java.time.LocalDate;
import java.util.List;

public interface RequestRepository {

    List<RequestEntity> getUserExercises(long userId, long exerciseId);
    Long createRequest(long userId, long exerciseId);
    RequestEntity assignExercise(RequestEntity requestEntity);


    void deleteExercise(long exerciseId);

}
