package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.domain.Message;
import org.example.persistence.JPAMessageRepository;
import org.example.persistence.MessageRepo;
import org.example.persistence.entity.MessageEntity;
import org.example.persistence.entity.UserEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MessageRepositoryImpl implements MessageRepo {
    private final JPAMessageRepository jpaRepo;
    @Override
    public List<MessageEntity> findConversation(Long senderId, Long receiverId) {
        return jpaRepo.findConversation(senderId, receiverId);
    }
    @Override
    public Optional<Long> findTrainerOfUser(Long userId)
    {
        return jpaRepo.findTrainerOfUser(userId);
    }

    @Override
    public MessageEntity saveMessage(MessageEntity message) {
        return jpaRepo.save(message);
    }

    @Override
    public List<MessageEntity> getChatHistory(Long userId, Long trainerId) {
        return jpaRepo.findConversation(userId, trainerId);
    }

    @Override
    public List<MessageEntity> findAllConversations(Long trainerId) {
        return jpaRepo.findAllConversations(trainerId);
    }

    @Override
    public List<UserEntity> findClientsOfTrainer(Long trainerId) {
        return null;
    }
}