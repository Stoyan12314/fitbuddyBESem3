package org.example.buisness;

import org.example.controller.RequestsResponds.CreateUserRequest;
import org.example.controller.RequestsResponds.CreateUserResponse;
import org.example.controller.RequestsResponds.GetUserResponse;

public interface UserManager {

        GetUserResponse getUsers();

        void deleteById(long userId);



    CreateUserResponse createUser(CreateUserRequest request);
}
