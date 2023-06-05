package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.exceptions.EmptyListExercises;
import org.example.buisness.RequestManager;
import org.example.controller.dto.CreateRequestRequest;
import org.example.controller.dto.RequestResponse;
import org.example.controller.converters.RequestConverter;
import org.example.domain.Request;
import org.example.persistence.RequestRepository;
import org.example.persistence.entity.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestManagerImpl implements RequestManager {
    private final RequestRepository requestRepository;

    @Override
    public Long createRequest(long userId, long exerciseId)
    {
        return null;
    }

    @Override
    public List<Request> getUserExercises(long userId, long exerciseId)
    {
        List<RequestEntity> requests = requestRepository.getUserExercises(userId,exerciseId);

        if (requests.isEmpty()) {
            throw new EmptyListExercises();
        }

        return RequestConverter.convert(requests);
    }
    @Override
    public void deleteExercise(long exerciseId)
    {
        requestRepository.deleteExercise(exerciseId);
    }

    @Override
    public RequestResponse assignExercise(CreateRequestRequest requestEntity) {
        RequestEntity response = requestRepository.assignExercise(RequestConverter.convertToRequestRequest(requestEntity));
        return RequestResponse.builder()
                .id(response.getId())
                .build();
    }


}
