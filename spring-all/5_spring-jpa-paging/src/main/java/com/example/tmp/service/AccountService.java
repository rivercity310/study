package com.example.tmp.service;

import com.example.tmp.domain.Account;
import com.example.tmp.domain.Email;
import com.example.tmp.dto.AccountDto;
import com.example.tmp.exception.AccountNotFoundException;
import com.example.tmp.exception.EmailDuplicationException;
import com.example.tmp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Account findById(Long id) {
        final Optional<Account> account = accountRepository.findById(id);
        account.orElseThrow(() -> new AccountNotFoundException(id));
        return account.get();
    }

    @Transactional(readOnly = true)
    public Account findByEmail(final Email email) {
        final Account account = accountRepository.findByEmail(email);
        if (account == null) throw new AccountNotFoundException(email);
        return account;
    }

    @Transactional(readOnly = true)
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public boolean isExistedEmail(Email email) {
        return accountRepository.findByEmail(email) != null;
    }

    public Account create(AccountDto.SignUpReq dto) {
        if (isExistedEmail(dto.getEmail()))
            throw new EmailDuplicationException(dto.getEmail());
        return accountRepository.save(dto.toEntity());
    }

    public Account updateMyAccount(Long id, AccountDto.MyAccountReq dto) {
        final Account account = findById(id);
        account.updateMyAccount(dto);
        return account;
    }
}
