package com.example.thingsFlow.password;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Validation {
    public boolean checkHashedPassword(String plainPassword, String storedPassword) {
        return BCrypt.checkpw(plainPassword, storedPassword);
    }
}
