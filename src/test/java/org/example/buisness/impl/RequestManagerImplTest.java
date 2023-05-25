package org.example.buisness.impl;

import org.example.buisness.exceptions.EmptyListExercises;
import org.example.controller.dto.RegisterRequest;
import org.example.domain.Request;
import org.example.persistence.RequestRepository;
import org.example.persistence.entity.RequestEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestManagerImplTest {

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private RequestManagerImpl requestManager;

    @Test
    @DisplayName(
            "Should throw an EmptyListExercises exception when there are no exercises for the given user")
    void getUserExercisesWhenNoExercisesThenThrowEmptyListExercisesException() {
        long userId = 1L;
        List<RequestEntity> emptyList = Collections.emptyList();

        when(requestRepository.getUserExercises(userId)).thenReturn(emptyList);

        assertThrows(EmptyListExercises.class, () -> requestManager.getUserExercises(userId));

        verify(requestRepository, times(1)).getUserExercises(userId);
    }

    @Test
    @DisplayName(
            "Should return a list of user exercises when there are exercises for the given user")
    void getUserExercisesWhenExercisesExist() {
        long userId = 1L;
        List<RequestEntity> requestEntities = new ArrayList<>();
        RequestEntity requestEntity =
                RequestEntity.builder()
                        .id(1L)
                        .date(LocalDate.now())
                        .userId(userId)
                        .exerciseId(1L)
                        .reps(10)
                        .weight(50.0)
                        .build();
        requestEntities.add(requestEntity);
        when(requestRepository.getUserExercises(userId)).thenReturn(requestEntities);

        List<Request> requests = requestManager.getUserExercises(userId);

        assertNotNull(requests);
        assertEquals(1, requests.size());
        Request request = requests.get(0);
        assertEquals(requestEntity.getId(), request.getId());
        assertEquals(requestEntity.getDate(), request.getDate());
        assertEquals(requestEntity.getUserId(), request.getUserId());
        assertEquals(requestEntity.getExerciseId(), request.getExerciseId());
        assertEquals(requestEntity.getReps(), request.getReps());
        assertEquals(requestEntity.getWeight(), request.getWeight());

        verify(requestRepository, times(1)).getUserExercises(userId);
    }





}