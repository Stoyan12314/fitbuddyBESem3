package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.persistence.RequestRepository;
import org.example.persistence.entity.RequestEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@AllArgsConstructor
public class RequestRepositoryImpl implements RequestRepository {
    @Override
    public Long createRequest(long userId, long exerciseId) {
        return null;
    }


}
