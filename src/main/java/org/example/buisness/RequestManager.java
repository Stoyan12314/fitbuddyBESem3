package org.example.buisness;

import org.example.domain.AccessToken;
import org.example.persistence.RequestRepository;

public interface RequestManager {

    public Long createRequest(long userId, long exerciseId);


}
