package com.martin.config;

import com.martin.entity.NotificationChannel;
import com.martin.strategy.EmailNotificationStrategy;
import com.martin.strategy.NotificationStrategy;
import com.martin.strategy.PushNotificationStrategy;
import com.martin.strategy.SmsNotificationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class NotificationConfig {

    @Bean
    public Map<NotificationChannel, NotificationStrategy> notificationStrategies( //
            SmsNotificationStrategy smsStrategy, //
            EmailNotificationStrategy emailStrategy, //
            PushNotificationStrategy pushStrategy) {
        Map<NotificationChannel, NotificationStrategy> strategies = new EnumMap<>(NotificationChannel.class);
        strategies.put(NotificationChannel.SMS, smsStrategy);
        strategies.put(NotificationChannel.EMAIL, emailStrategy);
        strategies.put(NotificationChannel.PUSH, pushStrategy);
        return strategies;
    }
}

