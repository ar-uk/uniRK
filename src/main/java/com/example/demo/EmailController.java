package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {

    @GetMapping("/test-email")
    public String showTestEmailPage(Model model) {
        // Add dummy data to test
        model.addAttribute("message", "This is a test email page.");
        return "test-email-page"; // Name of the new Thymeleaf template
    }
}