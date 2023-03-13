package com.example.tmp.repository;

import com.example.tmp.domain.Account;
import com.example.tmp.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(Email email);
}
