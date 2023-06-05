package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.MessageManager;
import org.example.buisness.UserManager;
import org.example.domain.Message;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private MessageManager messageManager;

    @PostMapping("/{userId}")
    public Optional<Long> getTrainerId(@PathVariable Long userId)
    {
        return messageManager.findTrainerOfUser(userId);

    }

    @GetMapping("/chat/{userId}/{trainerId}")
    public List<Message> getChatHistory(@PathVariable Long userId, @PathVariable Long trainerId) {
        return messageManager.getChatHistory(userId, trainerId);
    }
    @SendTo("chatroom/private")
    @MessageMapping("/private-message")
    public void privateChat(@RequestBody Message message) {
        Message savedMessage = messageManager.saveMessage(message);
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverId().toString(),"/private", message);

    }


}