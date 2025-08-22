package com.bankingapp.service;

import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();
    private final Random random = new Random();

    private final EmailService emailService;

    public OtpService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + random.nextInt(900000)); // 6-digit OTP
        otpStorage.put(email, otp);
        return otp;
    }

    public void sendOtp(String email) throws Exception {
        String otp = generateOtp(email);
        emailService.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);
    }

    public boolean validateOtp(String email, String otp) {
        return otp.equals(otpStorage.get(email));
    }
}
