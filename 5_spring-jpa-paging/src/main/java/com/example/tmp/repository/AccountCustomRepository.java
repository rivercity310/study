package com.example.tmp.repository;

import com.example.tmp.domain.Account;

import java.util.List;

public interface AccountCustomRepository {
    List<Account> findRecentlyRegistered(int limit);
}
