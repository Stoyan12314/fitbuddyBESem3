package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.ExerciseManager;
import org.example.controller.RequestsResponds.CreateExerciseRequest;
import org.example.controller.RequestsResponds.CreateExerciseResponse;
import org.example.controller.RequestsResponds.GetExercisesResponse;
import org.example.controller.converters.ExerciseConverter;
import org.example.domain.Exercise;
//import org.example.persistence.ExerciseRepository;
//import org.example.persistence.FakeExerciseRepositoryImpl;
import org.example.persistence.entity.ExerciseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseManagerImpl implements ExerciseManager {
    @Override
    public GetExercisesResponse getExercises() {
        return null;
    }

    @Override
    public void deleteExerciseById(long exerciseId) {

    }

    @Override
    public CreateExerciseResponse createExercise(CreateExerciseRequest request) {
        return null;
    }

//    private ExerciseRepository repo;
//    private  final FakeExerciseRepositoryImpl repo; //have the interface
//
//
//    @Override
//    public GetExercisesResponse getExercises() {
//        List<ExerciseEntity> results = repo.findAll();
//
//
//        final GetExercisesResponse response = new GetExercisesResponse();
//        List<Exercise> exercises = results
//                .stream()
//                .map(ExerciseConverter::convert)
//                .toList();
//        response.setExercises(exercises);
//
//
//        return response;
//    }
//
//
//
//
//
//
//    @Override
//    public void deleteExerciseById(long exerciseId) {
//        repo.deleteById(exerciseId);
//
//    }
//
//    @Override
//    public CreateExerciseResponse createExercise(CreateExerciseRequest request)
//    {
//
//
//
//        ExerciseEntity exercise = saveNewExercise(request);
//
//        return CreateExerciseResponse.builder()
//                .status("Done")
//                .build();
//
//
//    }
//    private ExerciseEntity saveNewExercise(CreateExerciseRequest request) {
//
//        ExerciseEntity newExercise = ExerciseEntity.builder()
//                .name(request.getName())
//
//                .description(request.getDescription())
//                .picture(request.getPicture())
//                .build();
//        return repo.saveExercise(newExercise);
//    }



}
