package com.martin.controller;

import com.martin.dto.NotificationRequest;
import com.martin.entity.NotificationHistory;
import com.martin.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotification_Success() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        doNothing().when(notificationService).sendNotification(any(NotificationRequest.class));

        // Act
        ResponseEntity<String> response = notificationController.sendNotification(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code should be 200 OK");
        assertEquals("Notification processed successfully", response.getBody(), "Response body should indicate success");

        // Verify that sendNotification was called once
        verify(notificationService, times(1)).sendNotification(request);
    }

    @Test
    void testSendNotification_Error() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        doThrow(new RuntimeException("Error")).when(notificationService).sendNotification(any(NotificationRequest.class));

        // Act
        ResponseEntity<String> response = notificationController.sendNotification(request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Status code should be 500 Internal Server Error");
        assertEquals("Error processing notification", response.getBody(), "Response body should indicate error");

        // Verify that sendNotification was called once
        verify(notificationService, times(1)).sendNotification(request);
    }

    @Test
    void testGetNotificationHistory() {
        // Arrange
        List<NotificationHistory> historyList = new ArrayList<>();
        when(notificationService.getNotificationHistory()).thenReturn(historyList);

        // Act
        List<NotificationHistory> result = notificationController.getNotificationHistory();

        // Assert
        assertEquals(historyList, result, "Returned history should match mock history");

        // Verify that getNotificationHistory was called once
        verify(notificationService, times(1)).getNotificationHistory();
    }
}
