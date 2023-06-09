package org.example.buisness;

import org.example.controller.dto.LoginResponse;
import org.example.controller.dto.LoginRequest;

public interface LoginManager {
    LoginResponse login(LoginRequest loginRequest);
}
