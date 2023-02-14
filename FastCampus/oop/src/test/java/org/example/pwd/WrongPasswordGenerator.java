package org.example.pwd;

import org.example.pwd.PasswordGenerator;

public class WrongPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "1234567";
    }
}
