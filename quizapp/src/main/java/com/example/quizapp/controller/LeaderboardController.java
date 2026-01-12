package com.example.quizapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.quizapp.repository.ScoreRepository;
import com.example.quizapp.entity.Score;
import java.util.List;

@Controller
public class LeaderboardController {

    @Autowired
    ScoreRepository scoreRepo;

    @GetMapping("/leaderboard")
    public String leaderboard(Model model) {
        // Matches the method name in the Repository exactly
        List<Score> scores = scoreRepo.findAllByOrderByScoreDesc();
        model.addAttribute("scores", scores);
        return "leaderboard";
    }
}