package com.martin.repository;

import com.martin.entity.Category;
import com.martin.entity.NotificationChannel;
import com.martin.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Use H2 database
@ActiveProfiles("test") // Use test profile
class UserDataRepositoryTest {

    @Autowired
    private UserDataRepository userDataRepository;

    @BeforeEach
    public void setUp() {
        userDataRepository.deleteAll(); // Clear the repository before each test
    }

    @Test
    void testFindBySubscribedTopics() {
        // Arrange: Create and save users
        User user1 = new User("Lionel Messi", "lionel@gila.com", "+456577",
                List.of(Category.SPORTS, Category.MOVIES), List.of(NotificationChannel.SMS, NotificationChannel.EMAIL));
        User user2 = new User("Cristiano Ronaldo", "cristiano@gila.com", "+86556443",
                List.of(Category.FINANCE), List.of(NotificationChannel.PUSH));

        userDataRepository.saveAll(List.of(user1, user2));

        // Act: Find users subscribed to SPORTS
        List<User> foundUsers = userDataRepository.findBySubscribedTopics(Category.SPORTS);

        // Assert: Check the results
        assertThat(foundUsers).hasSize(1);
        assertThat(foundUsers.get(0).getUsername()).isEqualTo("Lionel Messi");
    }

    @Test
    void testFindBySubscribedTopics_NoMatch() {
        // Arrange: Create and save a user with a different subscription
        User user = new User("Cristiano Ronaldo", "cristiano@gila.com", "+86556443",
                List.of(Category.FINANCE), List.of(NotificationChannel.PUSH));
        userDataRepository.save(user);

        // Act: Find users subscribed to SPORTS
        List<User> foundUsers = userDataRepository.findBySubscribedTopics(Category.SPORTS);

        // Assert: Check that no users were found
        assertThat(foundUsers).isEmpty();
    }
}
