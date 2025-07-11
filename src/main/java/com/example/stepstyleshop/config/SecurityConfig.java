package com.example.stepstyleshop.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /* Se deshabilita la autenticación al inicio */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilita la protección CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Permite el acceso a TODAS las rutas
                );

        return http.build();
    }
}