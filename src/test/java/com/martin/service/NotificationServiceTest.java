package com.martin.service;

import com.martin.dto.NotificationRequest;
import com.martin.entity.Category;
import com.martin.entity.NotificationChannel;
import com.martin.entity.NotificationHistory;
import com.martin.entity.User;
import com.martin.repository.NotificationHistoryRepository;
import com.martin.repository.UserDataRepository;
import com.martin.strategy.NotificationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private UserDataRepository userDataRepository;

    @Mock
    private Map<NotificationChannel, NotificationStrategy> strategies;

    @Mock
    private NotificationHistoryRepository historyRepository;

    @Mock
    private NotificationStrategy smsStrategy;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotification_Success() {
        // Arrange
        NotificationRequest request = new NotificationRequest() //
                .withCategory(Category.SPORTS) //
                .withMessage("Sports news!");
        User user = new User("Lionel Messi", "lionel.messi@gila.com", "+123456789",
                List.of(Category.SPORTS), List.of(NotificationChannel.SMS));

        when(userDataRepository.findBySubscribedTopics(Category.SPORTS)).thenReturn(List.of(user));
        when(strategies.get(NotificationChannel.SMS)).thenReturn(smsStrategy);

        // Act
        notificationService.sendNotification(request);

        // Assert
        verify(smsStrategy, times(1)).send(user, "Sports news!");
        verify(historyRepository, times(1)).save(any(NotificationHistory.class));
    }

    @Test
    void testSendNotification_Failure() {
        // Arrange
        NotificationRequest request = new NotificationRequest() //
                .withCategory(Category.FINANCE) //
                .withMessage("Finance news!");
        User user = new User("Gabriela Sabatini", "gabriela.sabatini@gila.com", "+987654321",
                List.of(Category.FINANCE), List.of(NotificationChannel.SMS));

        when(userDataRepository.findBySubscribedTopics(Category.FINANCE)).thenReturn(List.of(user));
        when(strategies.get(NotificationChannel.SMS)).thenReturn(smsStrategy);
        doThrow(new RuntimeException("SMS sending failed")).when(smsStrategy).send(user, "Finance news!");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> notificationService.sendNotification(request));

        // Verify that a failed notification history is saved
        verify(historyRepository, times(1)).save(any(NotificationHistory.class));
    }

    @Test
    void testGetNotificationHistory() {
        // Act
        notificationService.getNotificationHistory();

        // Assert
        verify(historyRepository, times(1)).findAll();
    }
}
