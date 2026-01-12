package com.example.quizapp.repository;

import com.example.quizapp.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    
    // Spring generates: SELECT * FROM scores ORDER BY score DESC
    List<Score> findAllByOrderByScoreDesc();
}