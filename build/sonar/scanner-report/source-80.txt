package org.example.persistence.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.MessageStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "sender_id")
    private Long senderId;

    @JoinColumn(name = "receiver_id")
    private Long receiverId;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MessageStatus status;
}
