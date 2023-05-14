package org.example.controller.converters;

import org.example.controller.RequestsResponds.CreateRequestRequest;
import org.example.domain.Exercise;
import org.example.domain.Request;
import org.example.domain.User;
import org.example.persistence.entity.RequestEntity;
import org.example.persistence.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestConverter {
    private RequestConverter() {
    }
    public static Request convertToRequest(RequestEntity requestEntity)
    {
        return Request.builder()
                .id(requestEntity.getId())
                .date(requestEntity.getDate())
                .userId(requestEntity.getUserId())
                .exerciseId(requestEntity.getExerciseId())
                .reps(requestEntity.getReps())
                .weight(requestEntity.getWeight())
                .build();
    }

    public static RequestEntity convertToRequestRequest(CreateRequestRequest createRequestRequest)
    {


        return RequestEntity.builder()
                .date(createRequestRequest.getDate())
                .userId(createRequestRequest.getUserId())
                .exerciseId(createRequestRequest.getExerciseId())
                .reps(createRequestRequest.getReps())
                .weight(createRequestRequest.getWeight())
                .build();
    }

    public static List<Request> convert(List<RequestEntity> requestEntities) {
        if (requestEntities == null) {
            return null;
        }

        List<Request> requests = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntities) {
            Request request = new Request();

            request.setId(requestEntity.getId());
            request.setReps(requestEntity.getReps());
            request.setWeight(requestEntity.getWeight());
            request.setDate(requestEntity.getDate());


            request.setUserId(requestEntity.getUserId());
            request.setExerciseId(requestEntity.getExerciseId());

            requests.add(request);
        }

        return requests;
    }
}
