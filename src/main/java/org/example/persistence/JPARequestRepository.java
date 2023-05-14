package org.example.persistence;

import org.example.persistence.entity.ExerciseEntity;
import org.example.persistence.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JPARequestRepository  extends JpaRepository<RequestEntity, Long> {
    @Query("SELECT r FROM RequestEntity r WHERE r.userId = :id")
    List<RequestEntity> getUserExercises(@Param("id") Long id);



    @Query("DELETE  FROM RequestEntity r WHERE r.exerciseId = :id")
    void deleteExercise(@Param("id") Long id);
}
