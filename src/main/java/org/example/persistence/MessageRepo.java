package org.example.persistence;

import org.example.domain.Message;
import org.example.persistence.entity.MessageEntity;
import org.example.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepo {
    Optional<Long> findTrainerOfUser(Long userId);

    List<MessageEntity> findConversation( Long senderId, Long receiverId);
    MessageEntity saveMessage(MessageEntity message);

    List<MessageEntity> getChatHistory(Long userId, Long trainerId);
    List<MessageEntity> findAllConversations( Long trainerId);

    List<UserEntity> findClientsOfTrainer( Long trainerId);
}
