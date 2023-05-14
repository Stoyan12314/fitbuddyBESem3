package org.example.persistence;

import org.example.persistence.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAExerciseRepository extends JpaRepository<ExerciseEntity, Long> {

}
