package org.example.buisness.impl;

import org.example.domain.ExerciseCount;
import org.example.domain.ExerciseIntensity;
import org.example.persistence.StatisticRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

class StatisticsManagerImplTest {

    @Test
    @DisplayName("Should return exercise count for a particular interval")
    void countUsersPerExerciseInIntervalTest() {

        StatisticRepo statisticRepo = mock(StatisticRepo.class);
        StatisticsManagerImpl statisticsManager = new StatisticsManagerImpl(statisticRepo);

        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);

        List<ExerciseCount> expectedCounts = Arrays.asList(
                new ExerciseCount("Exercise1", 5l),
                new ExerciseCount("Exercise2", 10l));

        when(statisticRepo.countUsersPerExerciseInInterval(startDate, endDate)).thenReturn(expectedCounts);

        List<ExerciseCount> result = statisticsManager.countUsersPerExerciseInInterval(startDate, endDate);

        assertEquals(expectedCounts.size(), result.size());
    }

    @Test
    @DisplayName("Should return exercise intensity for a particular interval")
    void calculateAverageExerciseIntensityTest() {
        StatisticRepo statisticRepo = mock(StatisticRepo.class);
        StatisticsManagerImpl statisticsManager = new StatisticsManagerImpl(statisticRepo);

        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);

        List<ExerciseIntensity> expectedIntensities = Arrays.asList(
                new ExerciseIntensity("Exercise1", 5.0),
                new ExerciseIntensity("Exercise2", 10.0));

        when(statisticRepo.calculateAverageExerciseIntensity(startDate, endDate)).thenReturn(expectedIntensities);

        List<ExerciseIntensity> result = statisticsManager.calculateAverageExerciseIntensity(startDate, endDate);

        assertEquals(expectedIntensities.size(), result.size());
    }
}