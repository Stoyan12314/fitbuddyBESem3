package org.example.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data

public class Message {
    private Long senderId;
    private Long receiverId;
    private String message;
    private LocalDateTime date;
    private MessageStatus status;

}
