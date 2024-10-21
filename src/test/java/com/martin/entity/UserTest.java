package com.martin.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        // Given
        String username = "rene_favaloro";
        String email = "rene@favaloro.com";
        String phone = "123-456-7890";
        List<Category> subscribedTopics = Arrays.asList(Category.SPORTS, Category.FINANCE);
        List<NotificationChannel> preferredChannels = Arrays.asList(NotificationChannel.EMAIL, NotificationChannel.SMS);

        // When
        User user = new User(username, email, phone, subscribedTopics, preferredChannels);

        // Then
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertEquals(subscribedTopics, user.getSubscribedTopics());
        assertEquals(preferredChannels, user.getPreferredChannels());
    }

    @Test
    void testUserWithEmptyFields() {
        // Given
        String username = "";
        String email = "";
        String phone = "";
        List<Category> subscribedTopics = List.of(); // No subscribed topics
        List<NotificationChannel> preferredChannels = List.of(); // No preferred channels

        // When
        User user = new User(username, email, phone, subscribedTopics, preferredChannels);

        // Then
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertTrue(user.getSubscribedTopics().isEmpty());
        assertTrue(user.getPreferredChannels().isEmpty());
    }

    @Test
    void testUserEquality() {
        // Given
        User user1 = new User("rene_favaloro", "rene@favaloro.com", "123-456-7890",
                List.of(Category.SPORTS), List.of(NotificationChannel.EMAIL));
        User user2 = new User("rene_favaloro", "rene@favaloro.com", "123-456-7890",
                List.of(Category.SPORTS), List.of(NotificationChannel.EMAIL));

        // Then
        assertEquals(user1, user2);
    }

}
