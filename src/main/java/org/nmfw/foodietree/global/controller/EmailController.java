package org.nmfw.foodietree.global.controller;

import lombok.RequiredArgsConstructor;
import org.nmfw.foodietree.domain.customer.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendHtmlEmail(@RequestParam String to, @RequestParam String code) {
        try {
            emailService.sendHtmlMessage(to, code);
            return "Password reset email sent successfully";
        } catch (MessagingException e) {
            return "Failed to send HTML email: " + e.getMessage();
        }
    }
}
