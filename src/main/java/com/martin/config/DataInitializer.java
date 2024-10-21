package com.martin.config;

import com.martin.entity.Category;
import com.martin.entity.NotificationChannel;
import com.martin.entity.User;
import com.martin.repository.UserDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserDataRepository userDataRepository) {
        return args -> {
            List<User> users = List.of(
                    new User("Lionel Messi", "lionel@gila.com", "+456577", List.of(Category.SPORTS, Category.MOVIES), List.of(NotificationChannel.SMS, NotificationChannel.EMAIL)),
                    new User("Cristiano Ronaldo", "cristiano@gila.com", "+86556443", List.of(Category.FINANCE), List.of(NotificationChannel.PUSH))
            );
            userDataRepository.saveAll(users);
        };
    }
}
