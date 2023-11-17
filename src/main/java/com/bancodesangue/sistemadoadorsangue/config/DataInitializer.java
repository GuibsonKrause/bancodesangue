package com.bancodesangue.sistemadoadorsangue.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bancodesangue.sistemadoadorsangue.model.UserEntity;
import com.bancodesangue.sistemadoadorsangue.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se o usuário já existe
        if (userRepository.findByUsername("wkadmin") == null) {
            UserEntity user = new UserEntity();
            user.setUsername("wkadmin");
            user.setPassword(passwordEncoder.encode("password123"));

            userRepository.save(user);
        }
    }
}
