package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.StatisticsManager;
import org.example.domain.ExerciseCount;
import org.example.domain.ExerciseIntensity;
import org.example.domain.Request;
import org.example.security.isauthenticated.IsAuthenticated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {
    private StatisticsManager statisticsManager;
    @IsAuthenticated
    @CrossOrigin(origins = "http://localhost:3000")
    @RolesAllowed("ROLE_ADMINISTRATION")
    @GetMapping("/exerciseCount")
    public ResponseEntity<List<ExerciseCount>> getQuarterlyExerciseCount(@RequestParam String  startDate, @RequestParam String  endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        List<ExerciseCount> count = statisticsManager.countUsersPerExerciseInInterval(start, end);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


    @IsAuthenticated
    @CrossOrigin(origins = "http://localhost:3000")
    @RolesAllowed("ROLE_CUSTOMER")
    @GetMapping("/exerciseIntensity")
    public ResponseEntity<List<ExerciseIntensity>> calculateAverageExerciseIntensity(@RequestParam String  startDate, @RequestParam String  endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        System.out.println("Start date : "+ start);
        List<ExerciseIntensity> count = statisticsManager.calculateAverageExerciseIntensity(start, end);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
