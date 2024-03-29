package com.example.FinancialTransactions.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/accounts/**").permitAll()
                        .requestMatchers("/currencies/**").permitAll()
                        .requestMatchers("/transactions/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll() // Allows preflight requests for CORS
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // Disabling CSRF using the new method
        // .httpBasic() or other authentication mechanism
        ;
        return http.build();
    }
}


