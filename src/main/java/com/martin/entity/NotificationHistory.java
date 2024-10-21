package com.martin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonPropertyOrder({"notification_id"})
public class NotificationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String userName;
    private String messageType;
    private String channel;
    private String message;
    private LocalDateTime timestamp;
    private boolean success;

    public NotificationHistory(Long userId, String userName, String messageType, String channel, String message, boolean success) {
        this.userId = userId;
        this.userName = userName;
        this.messageType = messageType;
        this.channel = channel;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.success = success;
    }

    @JsonProperty("notification_id")
    public Long getId() {
        return id;
    }

}

