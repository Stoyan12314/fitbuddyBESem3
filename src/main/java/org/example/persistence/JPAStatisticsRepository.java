package org.example.persistence;

import org.example.domain.ExerciseCount;
import org.example.domain.ExerciseIntensity;
import org.example.persistence.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.example.domain.ExerciseCount;
import java.time.LocalDate;
import java.util.List;

public interface JPAStatisticsRepository extends JpaRepository<RequestEntity, Long> {

@Query("SELECT NEW org.example.domain.ExerciseCount(ex.name, COUNT(DISTINCT re.userId)) " +
        "FROM RequestEntity re INNER JOIN ExerciseEntity ex ON re.exerciseId = ex.id " +
        "WHERE re.date >= :startDate AND re.date <= :endDate " +
        "GROUP BY ex.name")
    List<ExerciseCount> countUsersPerExerciseInInterval(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT NEW org.example.domain.ExerciseIntensity(ex.name, AVG(re.weight)) " +
            "FROM RequestEntity re INNER JOIN ExerciseEntity ex ON re.exerciseId = ex.id " +
            "WHERE re.date >= :startDate AND re.date <= :endDate " +
            "GROUP BY ex.name")
    List<ExerciseIntensity> calculateAverageExerciseIntensity(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);



}
