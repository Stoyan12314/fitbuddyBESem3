package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.RequestManager;
import org.example.domain.AccessToken;
import org.example.persistence.RequestRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestManagerImpl implements RequestManager {
    private AccessToken requestAccessToken;
    private final RequestRepository requestRepository;

    @Override
    public Long createRequest(long userId, long exerciseId) {
        return null;
    }
}
