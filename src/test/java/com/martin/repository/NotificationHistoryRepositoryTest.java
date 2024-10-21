package com.martin.repository;

import com.martin.entity.NotificationHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationHistoryRepositoryTest {

    @Mock
    private NotificationHistoryRepository notificationHistoryRepository;

    private NotificationHistory notificationHistory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a sample NotificationHistory object
        notificationHistory = new NotificationHistory();
        notificationHistory.setId(1L);
        notificationHistory.setMessage("Test notification message");
    }

    @Test
    void testFindById() {
        // Mock repository behavior
        when(notificationHistoryRepository.findById(1L)).thenReturn(Optional.of(notificationHistory));

        // Call repository method
        Optional<NotificationHistory> result = notificationHistoryRepository.findById(1L);

        // Validate the result
        assertTrue(result.isPresent());
        assertEquals(notificationHistory.getMessage(), result.get().getMessage());

        // Verify interactions with mock
        verify(notificationHistoryRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveNotificationHistory() {
        // Mock the save method
        when(notificationHistoryRepository.save(notificationHistory)).thenReturn(notificationHistory);

        // Call repository method
        NotificationHistory saved = notificationHistoryRepository.save(notificationHistory);

        // Validate the saved entity
        assertNotNull(saved);
        assertEquals(notificationHistory.getMessage(), saved.getMessage());

        // Verify interactions with mock
        verify(notificationHistoryRepository, times(1)).save(notificationHistory);
    }
}
