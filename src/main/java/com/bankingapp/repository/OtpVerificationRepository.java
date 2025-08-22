package com.bankingapp.repository;

import com.bankingapp.model.OtpVerification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpVerificationRepository extends MongoRepository<OtpVerification, String> {
    Optional<OtpVerification> findTopByUserIdAndOtpCodeOrderByCreatedAtDesc(String userId, String otpCode);
    void deleteByUserId(String userId);
}
