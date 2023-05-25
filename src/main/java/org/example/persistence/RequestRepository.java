package org.example.persistence;
import org.example.persistence.entity.RequestEntity;
import java.util.List;

public interface RequestRepository {
    Long createRequest(long userId, long exerciseId);
    RequestEntity assignExercise(RequestEntity requestEntity);

    List<RequestEntity> getUserExercises(long userId);
    void deleteExercise(long exerciseId);

}
