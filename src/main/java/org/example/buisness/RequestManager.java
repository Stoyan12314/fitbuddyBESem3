package org.example.buisness;

import org.example.controller.converters.RequestConverter;
import org.example.controller.dto.CreateRequestRequest;
import org.example.controller.dto.RequestResponse;
import org.example.domain.Request;

import java.time.LocalDate;
import java.util.List;

public interface RequestManager {

     Long createRequest(long userId, long exerciseId);
      List<Request> getUserExercises(long userId, long exerciseId);
     RequestResponse assignExercise(CreateRequestRequest requestEntity);
     void deleteExercise(long exerciseId);



}
