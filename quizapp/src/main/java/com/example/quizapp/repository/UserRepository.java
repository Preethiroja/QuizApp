package com.example.quizapp.repository;

import com.example.quizapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Spring Data JPA creates the SQL query automatically based on this name
    User findByUsernameAndPassword(String username, String password);
}