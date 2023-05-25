package org.example.buisness;

import org.example.controller.dto.RegisterRequest;
import org.example.controller.dto.RegisterResponse;

public interface RegisterManager {


    RegisterResponse createUser(RegisterRequest request);
}
