package org.example.persistence;

import org.example.domain.Message;
import org.example.domain.User;
import org.example.persistence.entity.MessageEntity;
import org.example.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JPAMessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query("SELECT m FROM MessageEntity m WHERE (m.senderId = :userId AND m.receiverId = :trainerId) OR (m.senderId = :trainerId AND m.receiverId = :userId) ORDER BY m.date DESC")
    List<MessageEntity> findConversation(@Param("userId") Long userId, @Param("trainerId") Long trainerId);

    @Query("SELECT m FROM MessageEntity m WHERE m.id = :trainerId OR m.id = :trainerId ORDER BY m.date DESC")
    List<MessageEntity> findAllConversations(@Param("trainerId") Long trainerId);
    @Query("SELECT m.senderId FROM MessageEntity m WHERE m.receiverId = :userId ORDER BY m.date ASC")
    Optional<Long> findTrainerOfUser(@Param("userId") Long userId);
    @Query("SELECT DISTINCT u FROM UserEntity u JOIN MessageEntity m ON u.id = m.id OR u.id = m.id WHERE (m.id = :trainerId OR m.id = :trainerId) AND u.role = 'USER'")
    List<UserEntity> findClientsOfTrainer(@Param("trainerId") Long trainerId);
}
