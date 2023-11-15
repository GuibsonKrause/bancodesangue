package com.bancodesangue.sistemadoadorsangue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .cors(cors -> cors.disable()) // Desabilita CORS
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll()) // Permite todas as requisições sem autenticação
                .httpBasic(basic -> basic.disable()); // Desabilita a autenticação HTTP Basic

        return http.build();
    }
}
