package com.bancodesangue.sistemadoadorsangue.controller;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bancodesangue.sistemadoadorsangue.dto.QRCodeDTO;
import com.bancodesangue.sistemadoadorsangue.dto.TwoFactorVerificationRequest;
import com.bancodesangue.sistemadoadorsangue.service.TwoFactorAuthService;

@RestController
@RequestMapping("/auth")
public class TwoFactorAuthController {

    private final TwoFactorAuthService twoFactorAuthService;

    public TwoFactorAuthController(TwoFactorAuthService twoFactorAuthService) {
        this.twoFactorAuthService = twoFactorAuthService;
    }

    @PostMapping("/2fa/enable")
    public ResponseEntity<QRCodeDTO> enableTwoFactorAuth(@RequestBody String userId) {
        // Aqui, você buscaria o usuário pelo id e geraria a chave secreta para ele
        String secretKey = twoFactorAuthService.generateSecretKey();
        // Suponha que 'qrCodeUrl' seja uma string que você obteve após a geração do QR Code
        String qrCodeUrl = twoFactorAuthService.generateQRCodeUrl(userId, secretKey);
        // Salve secretKey em um local seguro associado ao usuário
        // Gere o código QR e envie para o usuário
        return ResponseEntity.ok(new QRCodeDTO(secretKey, qrCodeUrl));
    }

    @PostMapping("/2fa/verify")
    public ResponseEntity<String> verifyTwoFactorAuth(@RequestBody TwoFactorVerificationRequest request) {
        try {
            int verificationCode = Integer.parseInt(request.getVerificationCode());
            boolean isAuthorized = twoFactorAuthService.authorize(request.getSecretKey(), verificationCode);

            if (isAuthorized) {
                return ResponseEntity.ok("2FA is enabled successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid verification code.");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification code must be a number.");
        }
    }

}
