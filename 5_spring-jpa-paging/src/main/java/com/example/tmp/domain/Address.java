package com.example.tmp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    @NotEmpty(message = "주소를 입력해주세요")
    @Column(nullable = false)
    private String address1;

    @NotEmpty(message = "주소를 입력해주세요")
    @Column(nullable = false)
    private String address2;

    @NotEmpty(message = "우편번호를 입력해주세요")
    @Column(nullable = false)
    private String zip;

    @Builder
    public Address(String address1, String address2, String zip) {
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
    }
}
