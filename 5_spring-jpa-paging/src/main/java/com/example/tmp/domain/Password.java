package com.example.tmp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    @Column(name = "password")
    private String value;

    @Column(name = "password_expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "password_failed_count")
    private Integer failedCount;

    @Column(name = "password_ttl")
    private Long ttl;

    @Builder
    public Password(final String value) {
        this.ttl = 1209_604L;     // 14 days
        this.value = encodePassword(value);
        this.expirationDate = extendExpirationDate();
    }

    private String encodePassword(final String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private LocalDateTime extendExpirationDate() {
        return LocalDateTime.now().plusSeconds(ttl);
    }
}
