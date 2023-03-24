package org.example.buisness;

import org.example.controller.RequestsResponds.LoginResponse;
import org.example.controller.RequestsResponds.LoginRequest;

public interface LoginManager {
    LoginResponse login(LoginRequest loginRequest);
}
