package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.domain.ExerciseCount;
import org.example.domain.ExerciseIntensity;
import org.example.persistence.JPAStatisticsRepository;
import org.example.persistence.StatisticRepo;
import org.example.persistence.entity.RequestEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
@AllArgsConstructor
public class StatisticsRepositoryImpl implements StatisticRepo {
    private final JPAStatisticsRepository jpaRepo;

    @Override
    public List<ExerciseCount> countUsersPerExerciseInInterval(LocalDate startDate, LocalDate endDate) {
        return jpaRepo.countUsersPerExerciseInInterval(startDate, endDate);
    }


    @Override
    public List<ExerciseIntensity> calculateAverageExerciseIntensity(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate)
    {
        return jpaRepo.calculateAverageExerciseIntensity(startDate, endDate);
    }

}
