package com.example.uccexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Permitir acceso a H2 console
                .requestMatchers("/h2-console/**").permitAll()
                // Permitir el endpoint raíz
                .requestMatchers("/").permitAll()
                // El resto requiere autenticación
                .anyRequest().authenticated()
            )
            // HTTP Basic para facilitar pruebas desde Postman
            .httpBasic(Customizer.withDefaults())
            // Configuración necesaria para que H2 console funcione con security
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
            .withUsername("admin")
            .password("{noop}admin123") // {noop} para plaintext en pruebas
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}