
package com.example.demo;

import com.example.demo.UserRepository;
import com.example.demo.EmailService;
import com.example.demo.User;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmailSendingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String showEmailFormPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "send-email";
    }

    @PostMapping("/send-email/{userId}")
    public String sendEmail(@PathVariable Long userId, 
                            @RequestParam String subject, 
                            @RequestParam String message) throws MessagingException {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        emailService.sendEmail(user.getEmail(), subject, message);
        return "redirect:/send-email?success";
    }
}
