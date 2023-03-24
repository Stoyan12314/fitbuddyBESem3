package org.example.persistence.impl;


import lombok.AllArgsConstructor;
import org.example.controller.converters.UserConverter;
import org.example.domain.User;
import org.example.persistence.UserRepository;
import org.example.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
//@AllArgsConstructor
public class FakeUserRepo implements UserRepository {

    private final List<User> users;

    public FakeUserRepo()
    {
        users = new ArrayList<>();
        User user = new User();
        user.setId(1l);
        user.setEmail("sgrozdev@gmail.com");
        users.add(user);
    }

    @Override
    public User findUserByEmail(String email) {
        for (User s: users)
        {
            if (s.getEmail().equals(email))
            {
                return s;
            }

        }
        return null;


    }

    @Override
    public Optional<User> findUserById(Long id) {

        for (User s: users)
        {
         if(s.getId().equals(id))
            {
                return Optional.ofNullable(s);

            }
        }
        return null;
    }

    @Override
    public UserEntity saveUser(User user)
    {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email(user.getEmail())
                .password(user.getPassword())
               .role(user.getRole())
                .firstName(user.getFirstName())
               .lastName(user.getLastName())
                .build();


        users.add(user);
        return userEntity;




    }
}
