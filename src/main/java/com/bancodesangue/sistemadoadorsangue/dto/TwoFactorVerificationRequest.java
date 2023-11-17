package com.bancodesangue.sistemadoadorsangue.dto;

public class TwoFactorVerificationRequest {
    
    private String secretKey;
    private String verificationCode;

    // Getters and setters for secretKey and verificationCode

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
