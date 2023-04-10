package org.example.pwd;

import org.example.pwd.PasswordGenerator;

public class CorrectPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "12345678";
    }
}
