package org.example.buisness;

import org.example.controller.RequestsResponds.CreateRequestRequest;
import org.example.controller.RequestsResponds.RequestResponse;
import org.example.domain.AccessToken;
import org.example.domain.Request;
import org.example.persistence.RequestRepository;
import org.example.persistence.entity.RequestEntity;

import java.util.List;

public interface RequestManager {

     Long createRequest(long userId, long exerciseId);
     List<Request> getUserExercises(long userId);
     RequestResponse assignExercise(CreateRequestRequest requestEntity);
     public void deleteExercise(long exerciseId);

}
