package org.example.buisness;

import org.example.controller.RequestsResponds.RegisterRequest;
import org.example.controller.RequestsResponds.RegisterResponse;

public interface RegisterManager {


    RegisterResponse createUser(RegisterRequest request);
}
