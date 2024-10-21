package com.martin.strategy;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.martin.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SmsNotificationStrategyTest {

    @Mock
    private User user;

    private SmsNotificationStrategy smsNotificationStrategy;

    @Mock
    private Appender<ILoggingEvent> mockAppender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the strategy to be tested
        smsNotificationStrategy = new SmsNotificationStrategy();

        // Get the logger instance used in the class
        Logger logger = (Logger) LoggerFactory.getLogger(SmsNotificationStrategy.class);

        // Create and add a mock appender to capture log events
        mockAppender = mock(Appender.class);
        logger.addAppender(mockAppender);
    }

    @Test
    public void testSendSmsNotification() {
        // Arrange: Set up the mock user and message
        when(user.getPhone()).thenReturn("123-456-7890");
        String message = "Test SMS message";

        // Argument captor to capture the logging event
        ArgumentCaptor<ILoggingEvent> captor = ArgumentCaptor.forClass(ILoggingEvent.class);

        // Act: Call the send method
        smsNotificationStrategy.send(user, message);

        // Assert: Verify that the correct log message was generated
        verify(mockAppender, times(1)).doAppend(captor.capture());
        String logMessage = captor.getValue().getFormattedMessage();

        // Check the log message against the info level
        assertTrue(logMessage.contains("Sending SMS to 123-456-7890: Test SMS message"));
    }
}
