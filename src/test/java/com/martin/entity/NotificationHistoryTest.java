package com.martin.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NotificationHistoryTest {

    @Test
    void testNotificationHistoryConstructorAndGetters() {
        // Given
        Long userId = 123L;
        String userName = "Alanis Morisette";
        String messageType = "Category";
        String channel = "Email";
        String message = "Notification message";
        boolean success = true;

        // When
        NotificationHistory history = new NotificationHistory(userId, userName, messageType, channel, message, success);

        // Then
        assertEquals(userId, history.getUserId());
        assertEquals(userName, history.getUserName());
        assertEquals(messageType, history.getMessageType());
        assertEquals(channel, history.getChannel());
        assertEquals(message, history.getMessage());
        assertTrue(history.isSuccess());
        assertNotNull(history.getTimestamp()); // Should be set during initialization
    }

    @Test
    void testSetters() {
        // Given
        NotificationHistory history = new NotificationHistory();
        Long userId = 123L;
        String userName = "Albert Einstein";
        String messageType = "Category";
        String channel = "Email";
        String message = "Notification message";
        boolean success = true;
        LocalDateTime timestamp = LocalDateTime.now();

        // When
        history.setUserId(userId);
        history.setUserName(userName);
        history.setMessageType(messageType);
        history.setChannel(channel);
        history.setMessage(message);
        history.setTimestamp(timestamp);
        history.setSuccess(success);

        // Then
        assertEquals(userId, history.getUserId());
        assertEquals(userName, history.getUserName());
        assertEquals(messageType, history.getMessageType());
        assertEquals(channel, history.getChannel());
        assertEquals(message, history.getMessage());
        assertEquals(timestamp, history.getTimestamp());
        assertTrue(history.isSuccess());
    }

    @Test
    void testDefaultConstructor() {
        // Given
        NotificationHistory history = new NotificationHistory();

        // When/Then
        assertNull(history.getUserId());
        assertNull(history.getUserName());
        assertNull(history.getMessageType());
        assertNull(history.getChannel());
        assertNull(history.getMessage());
        assertNull(history.getTimestamp());
        assertFalse(history.isSuccess()); // Default value of boolean is false
    }
}
