package com.martin.repository;

import com.martin.entity.Category;
import com.martin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataRepository extends JpaRepository<User, Long> {

    List<User> findBySubscribedTopics(Category topic);
}
