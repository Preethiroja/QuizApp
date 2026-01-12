package com.example.quizapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import com.example.quizapp.entity.Score;
import com.example.quizapp.repository.ScoreRepository;

@Controller
public class QuizController {

    @Autowired
    ScoreRepository scoreRepo;

    @GetMapping("/quiz")
    public String showQuiz(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        return "quiz";
    }

    @PostMapping("/submitQuiz")
public String submit(
        @RequestParam(required = false) String q1, @RequestParam(required = false) String q2,
        @RequestParam(required = false) String q3, @RequestParam(required = false) String q4,
        @RequestParam(required = false) String q5, @RequestParam(required = false) String q6,
        @RequestParam(required = false) String q7, @RequestParam(required = false) String q8,
        @RequestParam(required = false) String q9, @RequestParam(required = false) String q10,
        HttpSession session) {
        
    Object userIdObj = session.getAttribute("userId");
    if (userIdObj == null) return "redirect:/";

    int totalScore = 0;
    
    // Scoring Logic (10 points each)
    if ("new".equals(q1)) totalScore += 10;
    if ("web".equals(q2)) totalScore += 10;
    if ("boot".equals(q3)) totalScore += 10;
    if ("8080".equals(q4)) totalScore += 10;
    if ("comp".equals(q5)) totalScore += 10;
    if ("kit".equals(q6)) totalScore += 10;
    if ("sun".equals(q7)) totalScore += 10;
    if ("api".equals(q8)) totalScore += 10;
    if ("class".equals(q9)) totalScore += 10;
    if ("maven".equals(q10)) totalScore += 10;

    Score s = new Score();
    s.setUserId((int) userIdObj);
    s.setScore(totalScore);
    scoreRepo.save(s);

    return "redirect:/leaderboard";
}
}