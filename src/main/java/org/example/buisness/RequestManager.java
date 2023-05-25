package org.example.buisness;

import org.example.controller.dto.CreateRequestRequest;
import org.example.controller.dto.RequestResponse;
import org.example.domain.Request;
import java.util.List;

public interface RequestManager {

     Long createRequest(long userId, long exerciseId);
     List<Request> getUserExercises(long userId);
     RequestResponse assignExercise(CreateRequestRequest requestEntity);
     public void deleteExercise(long exerciseId);

}
