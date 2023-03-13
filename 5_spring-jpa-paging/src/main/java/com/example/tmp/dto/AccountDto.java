package com.example.tmp.dto;

import com.example.tmp.domain.Account;
import com.example.tmp.domain.Address;
import com.example.tmp.domain.Email;
import com.example.tmp.domain.Password;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AccountDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq {
        @Valid private Email email;
        @NotEmpty private String firstName;
        @NotEmpty private String lastName;
        private String password;
        @Valid private Address address;

        @Builder
        public SignUpReq(Email email, String firstName, String lastName, String password, Address address) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.address = address;
        }

        public Account toEntity() {
            return Account.builder()
                    .email(this.email)
                    .firstName(this.firstName)
                    .lastName(this.lastName)
                    .password(Password.builder().value(this.password).build())
                    .address(this.address)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyAccountReq {
        private Address address;

        @Builder
        public MyAccountReq(final Address address) {
            this.address = address;
        }
    }

    @Getter
    public static class Res {
        private final Email email;
        private final Password password;
        private final String firstName;
        private final String lastName;
        private final Address address;

        public Res(Account account) {
            this.email = account.getEmail();
            this.password = account.getPassword();
            this.firstName = account.getFirstName();
            this.lastName = account.getLastName();
            this.address = account.getAddress();
        }
    }
}
