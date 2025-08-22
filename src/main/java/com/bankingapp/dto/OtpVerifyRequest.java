package com.bankingapp.dto;

public class OtpVerifyRequest {
    private String userId;
    private String otpCode;

    public OtpVerifyRequest() {}
    public OtpVerifyRequest(String userId, String otpCode) {
        this.userId = userId; this.otpCode = otpCode;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getOtpCode() { return otpCode; }
    public void setOtpCode(String otpCode) { this.otpCode = otpCode; }
}
