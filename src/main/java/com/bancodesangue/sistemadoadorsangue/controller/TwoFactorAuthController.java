/*package com.bancodesangue.sistemadoadorsangue.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancodesangue.sistemadoadorsangue.dto.QRCodeDTO;
import com.bancodesangue.sistemadoadorsangue.service.TwoFactorAuthService;

@RestController
@RequestMapping("/auth")
public class TwoFactorAuthController {

    private final TwoFactorAuthService twoFactorAuthService;

    public TwoFactorAuthController(TwoFactorAuthService twoFactorAuthService) {
        this.twoFactorAuthService = twoFactorAuthService;
    }

    @PostMapping("/2fa/enable")
    public ResponseEntity<?> enableTwoFactorAuth(@RequestBody String userId) {
        // Aqui, você buscaria o usuário pelo id e geraria a chave secreta para ele
        String secretKey = twoFactorAuthService.generateSecretKey();
        // Salve secretKey em um local seguro associado ao usuário
        // Gere o código QR e envie para o usuário
        return ResponseEntity.ok().body(new QRCodeDTO(secretKey, qrCodeUrl));
    }

    @PostMapping("/2fa/verify")
    public ResponseEntity<?> verifyTwoFactorAuth(@RequestBody TwoFactorVerificationRequest request) {
        boolean isAuthorized = twoFactorAuthService.authorize(request.getSecretKey(), request.getVerificationCode());
        if (isAuthorized) {
            return ResponseEntity.ok().body("2FA is enabled successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Invalid verification code.");
        }
    }

    // DTO e Request classes aqui...
}
*/