package io.security.basicSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserManagementConfig {
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
