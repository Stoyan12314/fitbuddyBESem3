package org.example.buisness;

import org.example.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserManager {
        Page<User> getAllUsers(int page, int size);
      //  List<User> getUsersByEmail(String email);
        Page<User> getUsersByEmail(String email, int page, int size);
        Optional<User> getUser(Long id);

}
