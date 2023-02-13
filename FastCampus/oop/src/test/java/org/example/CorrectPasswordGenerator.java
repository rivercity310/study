package org.example;

public class CorrectPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "12345678";
    }
}
