package com.example.FinancialTransactions.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHash {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String hashPassword(String rawPassword){
       return passwordEncoder.encode(rawPassword);
    }
    public boolean checkPassword(String hashPassword, String rawPassword){
        return passwordEncoder.matches(hashPassword, rawPassword);
    }
}
