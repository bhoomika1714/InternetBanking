package com.bankingapp.controller;

import com.bankingapp.dto.LoginRequest;
import com.bankingapp.dto.OtpVerifyRequest;
import com.bankingapp.model.OtpVerification;
import com.bankingapp.model.User;
import com.bankingapp.repository.OtpVerificationRepository;
import com.bankingapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final UserRepository userRepository;
    private final OtpVerificationRepository otpRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    public AuthController(UserRepository userRepository, OtpVerificationRepository otpRepository) {
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        if (req.getIdentifier() == null || req.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "identifier and password required"));
        }

        // find by email or username
        User user = userRepository
                .findByEmailOrUsername(req.getIdentifier(), req.getIdentifier())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        // NOTE: plain-text compare for now; swap to BCrypt later
        if (!req.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        // generate a 6-digit OTP using SecureRandom
        int code = secureRandom.nextInt(900000) + 100000;
        String otpCode = String.valueOf(code);

        // clear old OTPs for this user (optional, keeps collection clean)
        otpRepository.deleteByUserId(user.getId());

        OtpVerification otp = new OtpVerification(
                user.getId(),
                otpCode,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5)
        );
        otpRepository.save(otp);

        // mock "send" — log to console
        System.out.println("OTP for userId=" + user.getId() + " is: " + otpCode);

        // return userId so client can call /verify-otp
        return ResponseEntity.ok(Map.of(
                "message", "OTP sent",
                "userId", user.getId()
        ));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerifyRequest req) {
        if (req.getUserId() == null || req.getOtpCode() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "userId and otpCode required"));
        }

        OtpVerification otp = otpRepository
                .findTopByUserIdAndOtpCodeOrderByCreatedAtDesc(req.getUserId(), req.getOtpCode())
                .orElse(null);

        if (otp == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid OTP"));
        }

        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "OTP expired"));
        }

        // mark user as verified
        User user = userRepository.findById(req.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "User not found"));
        }
        user.setVerified(true);
        userRepository.save(user);

        // OTP is single-use; delete it
        otpRepository.deleteByUserId(req.getUserId());

        return ResponseEntity.ok(Map.of(
                "message", "OTP verified. Login success.",
                "userId", user.getId(),
                "verified", user.isVerified()
        ));
    }
}
