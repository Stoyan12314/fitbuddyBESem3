package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.MessageManager;
import org.example.controller.converters.MessageConverter;
import org.example.controller.converters.UserConverter;
import org.example.domain.Message;
import org.example.domain.User;
import org.example.persistence.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageManagerImpl implements MessageManager {
    private final MessageRepo repo;

    @Override
    public List<Message> getChatHistory(Long userId, Long trainerId) {
        return repo.findConversation(userId, trainerId).stream()
                .map(MessageConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Long> findTrainerOfUser(Long userId) {
        return repo.findTrainerOfUser(userId);
    }

    @Override
    public List<Message> findConversation(Long senderId, Long receiverId) {
        return repo.findConversation(senderId, receiverId).stream()
                .map(MessageConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> findAllConversations(Long trainerId) {
        return repo.findAllConversations(trainerId).stream()
                .map(MessageConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findClientsOfTrainer(Long trainerId) {
        return repo.findClientsOfTrainer(trainerId).stream()
                .map(UserConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Message saveMessage(Message message) {
        return MessageConverter.fromEntity(repo.saveMessage(MessageConverter.toEntity(message)));
    }
}