package org.example;

public class WrongPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "1234567";
    }
}
