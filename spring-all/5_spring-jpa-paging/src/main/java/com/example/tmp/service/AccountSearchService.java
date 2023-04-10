package com.example.tmp.service;

import com.example.tmp.domain.Account;
import com.example.tmp.domain.AccountSearchType;
import com.example.tmp.domain.QAccount;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class AccountSearchService extends QuerydslRepositorySupport {
    public AccountSearchService() {
        super(Account.class);
    }

    public Page<Account> search(AccountSearchType type, String value, Pageable pageable) {
        final QAccount account = QAccount.account;
        final JPQLQuery<Account> query;

        switch (type) {
            case EMAIL -> query = from(account).
                    where(account.email.value.likeIgnoreCase(value + "%"));
            case NAME -> query = from(account).
                    where(account.firstName.likeIgnoreCase(value + "%").or(account.lastName.likeIgnoreCase(value + "%")));
            case ALL -> query = from(account).fetchAll();
            default -> throw new IllegalArgumentException();
        }

        List<Account> accounts = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, query).fetch();
        return new PageImpl<>(accounts, pageable, query.fetchCount());
    }
}
