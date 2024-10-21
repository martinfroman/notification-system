package com.martin.strategy;

import com.martin.entity.User;

public interface NotificationStrategy {
    void send(User user, String message);
}
