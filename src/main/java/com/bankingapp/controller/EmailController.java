package com.bankingapp.controller;

import com.bankingapp.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendTestEmail(@RequestParam String to) {
        // Directly return the service response (success or error)
        return emailService.sendEmail(to, "Test Email", "Hello from Internet Banking App!");
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String to, @RequestParam String otp) {
        return emailService.sendOtpEmail(to, otp);
    }
}
