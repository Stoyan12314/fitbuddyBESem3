package org.example.persistence;

import org.example.persistence.entity.ExerciseEntity;
import org.example.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@Repository
public class FakeUserRepositoryImpl implements UserRepository {
    private static long NEXT_ID = 1;

    private final List<UserEntity> savedUsers;

    public FakeUserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }


    @Override
    public List<UserEntity> findAll() {
        return Collections.unmodifiableList(savedUsers);
    }

    @Override
    public void deleteById(long userId) {

        Iterator<UserEntity> iterator = savedUsers.iterator();
        while (iterator.hasNext()) {
            UserEntity exercise = iterator.next();
            if (exercise.getId().equals(userId)) {
                iterator.remove();
            }

        }

    }


    @Override
    public UserEntity saveUser(UserEntity user) {
        if (user.getId() == null) {
            user.setId(NEXT_ID);
            NEXT_ID++;
            this.savedUsers.add(user);
        }
        return user;
    }

}
