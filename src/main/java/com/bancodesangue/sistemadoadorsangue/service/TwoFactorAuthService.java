package com.bancodesangue.sistemadoadorsangue.service;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

@Service
public class TwoFactorAuthService {

    private final GoogleAuthenticator gAuth;

    public TwoFactorAuthService() {
        GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder().build();
        this.gAuth = new GoogleAuthenticator(config);
    }

    public boolean authorize(String secretKey, int verificationCode) {
        return gAuth.authorize(secretKey, verificationCode);
    }

    public String generateQRCodeUrl(String username, String secretKey) {
        // Suponha que "issuer" seja o nome da sua aplicação ou organização
        String issuer = "MinhaAplicacao";
        try {
            // Codificando a chave secreta em Base32 (sem padding)
            Base32 base32 = new Base32();
            String encodedKey = base32.encodeToString(Hex.decodeHex(secretKey)).replace("=", "");

            // Criando o URL que será codificado no QR Code
            // O formato do URL é específico do Google Authenticator
            String qrCodeUrl = String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", 
                                             issuer, username, encodedKey, issuer);
            return qrCodeUrl;
        } catch (Exception e) {
            // Trate exceções conforme necessário
            return null;
        }
    }

    // Método para gerar a chave secreta
    public String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }

    // Métodos adicionais para enviar email com o código QR, validar senha, etc.
}
