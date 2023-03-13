package com.example.tmp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({ "id", "host" })
public class Email {
    @jakarta.validation.constraints.Email
    @Column(name = "email", nullable = false, unique = true)
    private String value;

    @Builder
    public Email(String value) {
        this.value = value;
    }

    public static Email of(String email) {
        return new Email(email);
    }

    public String getHost() {
        int idx = value.indexOf("@");
        return value.substring(idx + 1);
    }

    public String getId() {
        int idx = value.indexOf("@");
        return value.substring(0, idx);
    }
}
