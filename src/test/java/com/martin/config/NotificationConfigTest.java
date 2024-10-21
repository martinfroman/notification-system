package com.martin.config;

import com.martin.entity.NotificationChannel;
import com.martin.strategy.EmailNotificationStrategy;
import com.martin.strategy.NotificationStrategy;
import com.martin.strategy.PushNotificationStrategy;
import com.martin.strategy.SmsNotificationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

class NotificationConfigTest {

    private NotificationConfig notificationConfig;

    @Mock
    private SmsNotificationStrategy smsStrategy;

    @Mock
    private EmailNotificationStrategy emailStrategy;

    @Mock
    private PushNotificationStrategy pushStrategy;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        openMocks(this);
        // Initialize the NotificationConfig class
        notificationConfig = new NotificationConfig();
    }

    @Test
    void testNotificationStrategiesConfiguration() {
        // Call the method under test
        Map<NotificationChannel, NotificationStrategy> strategies = notificationConfig.notificationStrategies( //
                smsStrategy, emailStrategy, pushStrategy);

        // Assert that the map is not null
        assertNotNull(strategies, "Strategies map should not be null");

        // Assert that the correct strategies are assigned to the correct channels
        assertEquals(smsStrategy, strategies.get(NotificationChannel.SMS), "SMS strategy should be correctly mapped");
        assertEquals(emailStrategy, strategies.get(NotificationChannel.EMAIL), "Email strategy should be correctly mapped");
        assertEquals(pushStrategy, strategies.get(NotificationChannel.PUSH), "Push strategy should be correctly mapped");

        // Assert that there are exactly 3 entries in the map
        assertEquals(3, strategies.size(), "Strategies map should contain 3 entries");
    }
}
