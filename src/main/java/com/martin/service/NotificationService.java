package com.martin.service;

import com.martin.dto.NotificationRequest;
import com.martin.entity.NotificationChannel;
import com.martin.entity.NotificationHistory;
import com.martin.entity.User;
import com.martin.repository.NotificationHistoryRepository;
import com.martin.repository.UserDataRepository;
import com.martin.strategy.NotificationStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    private final UserDataRepository userDataRepository;
    private final Map<NotificationChannel, NotificationStrategy> strategies;
    private final NotificationHistoryRepository historyRepository;

    public NotificationService(UserDataRepository userDataRepository, //
                               Map<NotificationChannel, NotificationStrategy> strategies, //
                               NotificationHistoryRepository historyRepository) {
        this.userDataRepository = userDataRepository;
        this.strategies = strategies;
        this.historyRepository = historyRepository;
    }

    public void sendNotification(NotificationRequest request) {
        List<User> users = userDataRepository.findBySubscribedTopics(request.getCategory());
        for (User user : users) {
            if (user.getSubscribedTopics().contains(request.getCategory())) {
                for (NotificationChannel channel : user.getPreferredChannels()) {
                    NotificationStrategy strategy = strategies.get(channel);
                    if (strategy != null) {
                        try {
                            strategy.send(user, request.getMessage());
                            historyRepository.save(new NotificationHistory( //
                                    user.getId(), //
                                    user.getUsername(), //
                                    request.getCategory().name(), //
                                    channel.name(), //
                                    request.getMessage(), //
                                    true //
                            ));
                        } catch (Exception e) {
                            historyRepository.save(new NotificationHistory( //
                                    user.getId(), //
                                    user.getUsername(), //
                                    request.getCategory().name(), //
                                    channel.name(), //
                                    request.getMessage(), //
                                    false //
                            ));
                            throw e;
                        }
                    }
                }
            }
        }
    }

    public List<NotificationHistory> getNotificationHistory() {
        return historyRepository.findAll();
    }
}
