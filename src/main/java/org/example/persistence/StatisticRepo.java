package org.example.persistence;

import org.example.domain.ExerciseCount;
import org.example.domain.ExerciseIntensity;
import org.example.persistence.entity.RequestEntity;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StatisticRepo {
    List<ExerciseCount> countUsersPerExerciseInInterval(LocalDate startDate,LocalDate endDate);
    List<ExerciseIntensity> calculateAverageExerciseIntensity(LocalDate startDate, LocalDate endDate);

}