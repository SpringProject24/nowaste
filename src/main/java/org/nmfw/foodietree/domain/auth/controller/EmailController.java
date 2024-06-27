package org.nmfw.foodietree.domain.auth.controller;

import org.nmfw.foodietree.domain.auth.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send-reset-email")
    public String sendVerificationCode(@RequestParam String to) {
        try {
            emailService.sendResetVerificationCode(to);
            return "Password reset email sent successfully";
        } catch (Exception e) {
            return "Failed to send password reset email: " + e.getMessage();
        }
    }

    @GetMapping("/verify-reset-code")
    public String verifyResetCode(@RequestParam String email, @RequestParam String code) {
        if (emailService.verifyCode(email, code)) {
            return "Verification successful";
        } else {
            return "Verification failed or code expired";
        }
    }
}
