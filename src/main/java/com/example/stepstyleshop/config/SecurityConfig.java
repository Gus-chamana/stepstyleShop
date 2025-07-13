package com.example.stepstyleshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 1. Permite el acceso a TODAS las rutas de tu aplicación.
                        .requestMatchers("/**").permitAll()
                )
                // 2. Deshabilita el login y logout, ya que no son necesarios.
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())
                // 3. Deshabilita CSRF (Cross-Site Request Forgery).
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}