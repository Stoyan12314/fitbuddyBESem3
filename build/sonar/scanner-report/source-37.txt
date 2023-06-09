package org.example.controller.converters;

import org.example.domain.Message;
import org.example.persistence.entity.MessageEntity;

public class MessageConverter {
    private MessageConverter() {
    }
    public static Message fromEntity(MessageEntity entity) {
        Message message = new Message();
        message.setSenderId(entity.getSenderId());
        message.setReceiverId(entity.getReceiverId());
        message.setMessage(entity.getMessage());
        message.setDate(entity.getDate());
        message.setStatus(entity.getStatus());
        return message;
    }

    public static MessageEntity toEntity(Message message) {
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(message.getSenderId());
        entity.setReceiverId(message.getReceiverId());
        entity.setMessage(message.getMessage());
        entity.setDate(message.getDate());
        entity.setStatus(message.getStatus());
        return entity;
    }

}
