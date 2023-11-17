package com.bancodesangue.sistemadoadorsangue.dto;

public class QRCodeDTO {
    private String secretKey;
    private String qrCodeUrl;

    public QRCodeDTO(String secretKey, String qrCodeUrl) {
        this.secretKey = secretKey;
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }
}
