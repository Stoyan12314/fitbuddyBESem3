//package org.example.persistence.impl;
//
//import lombok.AllArgsConstructor;
//import org.example.controller.converters.UserConverter;
//import org.example.domain.User;
//import org.example.persistence.JPAExerciseRepository;
//import org.example.persistence.JPAUserRepository;
//import org.example.persistence.UserRepository;
//import org.example.persistence.entity.UserEntity;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@AllArgsConstructor
//public class UserRepositryImpl implements UserRepository {
//    private final JPAUserRepository jpaRepo;
//
//
//    @Override
//    public User findUserByEmail(String email) {
//        UserEntity user = jpaRepo.findFirstByEmail(email);
//        if(user != null) {
//            return User.builder()
//                    .email(user.getEmail())
//                    .password(user.getPassword())
//                    .id(user.getId())
//                    .role(user.getRole())
//                    .build();
//        }
//        return null;
//
//    }
//
//    @Override
//    public Optional<User> findUserById(Long id) {
//        Optional<UserEntity> entity = jpaRepo.findById(id);
//        return entity.map(UserConverter::convert);
//    }
//
//    @Override
//    public UserEntity saveUser(User user) {
//        UserEntity userEntity = UserEntity.builder()
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .role(user.getRole())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .build();
//        jpaRepo.save(userEntity);
//        return userEntity;
//    }
//}
