package com.example.tmp.domain;

import com.example.tmp.exception.PasswordFailedExceededException;
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
    @Column(name = "password", nullable = false)
    private String value;

    @Column(name = "password_expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "password_failed_count", nullable = false)
    private Integer failedCount;

    @Column(name = "password_ttl")
    private Long ttl;

    @Builder
    public Password(final String value) {
        this.failedCount = 0;
        this.ttl = 1209_604L;     // 14 days
        this.value = encodePassword(value);
        this.expirationDate = extendExpirationDate();
    }

    public boolean isMatched(final String rawPassword) {
        if (failedCount >= 5)
            throw new PasswordFailedExceededException();

        final boolean matches = isMatches(rawPassword);
        updateFailedCount(matches);
        return matches;
    }

    public void changePassword(final String newPassword, final String oldPassword) {
        if (isMatched(oldPassword)) {
            this.value = encodePassword(newPassword);
            this.expirationDate = extendExpirationDate();
        }
    }

    public boolean isExpiration() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

    private String encodePassword(final String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private LocalDateTime extendExpirationDate() {
        return LocalDateTime.now().plusSeconds(ttl);
    }

    private boolean isMatches(String rawPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, this.value);
    }

    private void updateFailedCount(boolean matches) {
        if (matches) this.failedCount = 0;
        else this.failedCount++;
    }
}
