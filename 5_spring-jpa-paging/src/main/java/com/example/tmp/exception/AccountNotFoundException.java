package com.example.tmp.exception;

import com.example.tmp.domain.Email;
import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {
    private Long id;
    private Email email;

    public AccountNotFoundException(Long id) {
        this.id = id;
    }

    public AccountNotFoundException(Email email) {
        this.email = email;
    }
}
