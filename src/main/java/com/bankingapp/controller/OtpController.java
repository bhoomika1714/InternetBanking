package com.bankingapp.controller;

import com.bankingapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String sendOtp(@RequestParam String email) {
        try {
            String otp = String.valueOf((int)((Math.random() * 900000) + 100000)); // 6 digit OTP
            emailService.sendOtpEmail(email, otp);
            return "OTP sent to " + email;
        } catch (Exception e) {
            return "Error sending OTP: " + e.getMessage();
        }
    }
}
