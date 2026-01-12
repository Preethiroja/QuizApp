package com.example.quizapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import com.example.quizapp.entity.User;
import com.example.quizapp.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepo;

    // Show Login Page
    @GetMapping("/")
    public String loginPage() {
        return "login"; 
    }

    // Show Register Page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; 
    }

    // Handle Registration
    @PostMapping("/register")
    public String register(User user) {
        userRepo.save(user);
        return "redirect:/"; 
    }

    // Handle Login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User u = userRepo.findByUsernameAndPassword(username, password);
        
        if (u != null) {
            // Store User ID in session for other controllers to use
            session.setAttribute("userId", u.getId());
            
            // FIX: Use redirect to trigger the QuizController's @GetMapping
            return "redirect:/quiz"; 
        }
        
        // If login fails, stay on login page and show error
        model.addAttribute("error", "Invalid username or password!");
        return "login";
    }
}