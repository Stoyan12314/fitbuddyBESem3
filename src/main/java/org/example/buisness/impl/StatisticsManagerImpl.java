package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.RequestManager;
import org.example.buisness.StatisticsManager;
import org.example.controller.converters.RequestConverter;
import org.example.domain.ExerciseCount;
import org.example.domain.ExerciseIntensity;
import org.example.domain.Request;
import org.example.persistence.ExerciseRepo;
import org.example.persistence.RequestRepository;
import org.example.persistence.StatisticRepo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class StatisticsManagerImpl implements StatisticsManager {
    private StatisticRepo requestRepository;
@Override
    public List<ExerciseCount> countUsersPerExerciseInInterval(LocalDate startDate, LocalDate endDate) {
        return requestRepository.countUsersPerExerciseInInterval(startDate, endDate);
    }

    @Override
    public List<ExerciseIntensity> calculateAverageExerciseIntensity(LocalDate startDate, LocalDate endDate)
    {
        return requestRepository.calculateAverageExerciseIntensity(startDate, endDate);
    }

}
