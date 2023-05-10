package org.example.persistence;

import org.example.domain.Request;
import org.example.persistence.entity.RequestEntity;

import java.util.List;

public interface RequestRepository {
    Long createRequest(long userId, long exerciseId);
}
