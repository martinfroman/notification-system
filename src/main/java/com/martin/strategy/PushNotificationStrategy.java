package com.martin.strategy;

import com.martin.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationStrategy implements NotificationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(PushNotificationStrategy.class);

    @Override
    public void send(User user, String message) {
        logger.info("Sending Push Notification to {}: {}", user.getPhone(), message);
    }

}
