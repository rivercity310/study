package com.example.tmp.exception;

import com.example.tmp.domain.Email;
import lombok.Getter;

@Getter
public class EmailDuplicationException extends RuntimeException {
    private final Email email;
    private final String field;

    public EmailDuplicationException(Email email) {
        this.field = "email";
        this.email = email;
    }
}
