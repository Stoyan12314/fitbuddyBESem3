package org.example.buisness.impl;

import org.example.controller.converters.MessageConverter;
import org.example.controller.converters.UserConverter;
import org.example.domain.Message;
import org.example.domain.User;
import org.example.persistence.MessageRepo;
import org.example.persistence.entity.MessageEntity;
import org.example.persistence.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

class MessageManagerImplTest {

    @Test
    @DisplayName("Should return chat history between a user and a trainer")
    void getChatHistoryTest() {
        MessageRepo messageRepo = mock(MessageRepo.class);
        MessageManagerImpl messageManager = new MessageManagerImpl(messageRepo);

        Long userId = 1L;
        Long trainerId = 2L;
        List<MessageEntity> messageEntities = List.of(new MessageEntity(), new MessageEntity());

        when(messageRepo.findConversation(userId, trainerId)).thenReturn(messageEntities);

        List<Message> messages = messageManager.getChatHistory(userId, trainerId);

        assertNotNull(messages);
        assertEquals(messageEntities.stream().map(MessageConverter::fromEntity).collect(Collectors.toList()), messages);
    }

    @Test
    @DisplayName("Should return trainer of a user")
    void findTrainerOfUserTest() {
        MessageRepo messageRepo = mock(MessageRepo.class);
        MessageManagerImpl messageManager = new MessageManagerImpl(messageRepo);

        Long userId = 1L;
        Long trainerId = 2L;

        when(messageRepo.findTrainerOfUser(userId)).thenReturn(Optional.of(trainerId));

        Optional<Long> returnedTrainerId = messageManager.findTrainerOfUser(userId);

        assertTrue(returnedTrainerId.isPresent());
        assertEquals(trainerId, returnedTrainerId.get());
    }

    @Test
    @DisplayName("Should return conversation between a sender and a receiver")
    void findConversationTest() {
        MessageRepo messageRepo = mock(MessageRepo.class);
        MessageManagerImpl messageManager = new MessageManagerImpl(messageRepo);

        Long senderId = 1L;
        Long receiverId = 2L;
        List<MessageEntity> messageEntities = List.of(new MessageEntity(), new MessageEntity());

        when(messageRepo.findConversation(senderId, receiverId)).thenReturn(messageEntities);

        List<Message> messages = messageManager.findConversation(senderId, receiverId);

        assertNotNull(messages);
        assertEquals(messageEntities.stream().map(MessageConverter::fromEntity).collect(Collectors.toList()), messages);
    }

    @Test
    @DisplayName("Should return all conversations for a trainer")
    void findAllConversationsTest() {
        MessageRepo messageRepo = mock(MessageRepo.class);
        MessageManagerImpl messageManager = new MessageManagerImpl(messageRepo);

        Long trainerId = 2L;
        List<MessageEntity> messageEntities = List.of(new MessageEntity(), new MessageEntity());

        when(messageRepo.findAllConversations(trainerId)).thenReturn(messageEntities);

        List<Message> messages = messageManager.findAllConversations(trainerId);

        assertNotNull(messages);
        assertEquals(messageEntities.stream().map(MessageConverter::fromEntity).collect(Collectors.toList()), messages);
    }

    @Test
    @DisplayName("Should return clients of a trainer")
    void findClientsOfTrainerTest() {
        MessageRepo messageRepo = mock(MessageRepo.class);
        MessageManagerImpl messageManager = new MessageManagerImpl(messageRepo);

        Long trainerId = 2L;
        List<UserEntity> userEntities = List.of(new UserEntity(), new UserEntity());

        when(messageRepo.findClientsOfTrainer(trainerId)).thenReturn(userEntities);

        List<User> users = messageManager.findClientsOfTrainer(trainerId);

        assertNotNull(users);
        assertEquals(userEntities.stream().map(UserConverter::convert).collect(Collectors.toList()), users);
    }

    @Test
    @DisplayName("Should save a message and return the saved message")
    void saveMessageTest() {
        MessageRepo messageRepo = mock(MessageRepo.class);
        MessageManagerImpl messageManager = new MessageManagerImpl(messageRepo);

        Message message = new Message();
        MessageEntity messageEntity = MessageConverter.toEntity(message);

        when(messageRepo.saveMessage(messageEntity)).thenReturn(messageEntity);

        Message savedMessage = messageManager.saveMessage(message);

        assertNotNull(savedMessage);
        assertEquals(MessageConverter.fromEntity(messageEntity), savedMessage);
    }
}