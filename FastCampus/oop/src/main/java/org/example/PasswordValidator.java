package org.example;

public class PasswordValidator {
    public static void validate(String password) {
        int length = password.length();
        if (length < 8 || length > 12) {
            String wrong_password_length_exception_message = "비밀번호 자릿수 확인";
            throw new IllegalArgumentException(wrong_password_length_exception_message);
        }
    }
}
