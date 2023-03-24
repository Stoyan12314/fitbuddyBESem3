package org.example.buisness;

import org.example.domain.User;

import java.util.Optional;

public interface UserManager {

        Optional<User> getUser(Long id);

}
