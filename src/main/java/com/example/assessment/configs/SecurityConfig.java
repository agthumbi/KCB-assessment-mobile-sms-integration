package com.example.assessment.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",      // OpenAPI docs
                                "/swagger-ui/**",       // Swagger UI
                                "/swagger-ui.html",     // Swagger HTML page
                                "/public/**"            // Other public endpoints
                        ).permitAll()  // 👈 Allow Swagger without authentication

                        .anyRequest().authenticated() // Require OAuth2 for everything else
                )
                .oauth2Login(withDefaults()) // Enable OAuth2 login
                .logout(logout -> logout.logoutSuccessUrl("/public"));

        return http.build();
    }
}

