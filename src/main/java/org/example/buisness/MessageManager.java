package org.example.buisness;

import org.example.domain.Message;
import org.example.domain.User;
import org.example.persistence.entity.MessageEntity;
import org.example.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface MessageManager {
    Optional<Long> findTrainerOfUser(Long userId);

    List<Message> findConversation(Long senderId, Long receiverId);

    List<Message> getChatHistory(Long userId, Long trainerId);
    List<Message> findAllConversations( Long trainerId);

    List<User> findClientsOfTrainer(Long trainerId);
    Message saveMessage(Message message);

}
