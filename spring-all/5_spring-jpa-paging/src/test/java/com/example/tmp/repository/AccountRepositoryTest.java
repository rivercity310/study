package com.example.tmp.repository;

import com.example.tmp.domain.Account;
import com.example.tmp.domain.Email;
import com.example.tmp.domain.QAccount;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    private final QAccount qAccount = QAccount.account;

    @Test
    public void findRecentlyRegisteredTest() {
        List<Account> accounts = accountRepository.findRecentlyRegistered(2);
        assertThat(accounts.size()).isEqualTo(2);
    }

    @Test
    public void predicate_test_001() {
        Predicate predicate = qAccount.email.eq(Email.of("h970126@gmail.com"));
        boolean exists = accountRepository.exists(predicate);
        assertThat(exists).isTrue();
    }
}