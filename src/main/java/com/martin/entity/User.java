package com.martin.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String phone;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Category> subscribedTopics;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<NotificationChannel> preferredChannels;

    public User(String username, String email, String phone, List<Category> subscribedTopics, List<NotificationChannel> preferredChannels) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.subscribedTopics = subscribedTopics;
        this.preferredChannels = preferredChannels;
    }

}
