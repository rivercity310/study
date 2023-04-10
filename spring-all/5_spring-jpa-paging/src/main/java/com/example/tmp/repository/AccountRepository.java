package com.example.tmp.repository;

import com.example.tmp.domain.Account;
import com.example.tmp.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AccountRepository extends
        JpaRepository<Account, Long>,
        AccountCustomRepository,
        QuerydslPredicateExecutor<Account>
{
    Account findByEmail(Email email);
    boolean existsByEmail(Email email);
}
