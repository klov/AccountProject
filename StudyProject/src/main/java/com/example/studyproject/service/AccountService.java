package com.example.studyproject.service;

import com.example.studyproject.repository.AccountEventBuss;
import com.example.studyproject.repository.AccountRepository;
import com.example.studyproject.service.model.Account;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Service
public class AccountService {

    private final AccountEventBuss accountEventBuss;
    private final AccountRepository accountRepository;

    public AccountService(AccountEventBuss accountEventBuss, AccountRepository accountRepository) {
        this.accountEventBuss = accountEventBuss;
        this.accountRepository = accountRepository;
    }

    public Account fillAccount(@NotNull String accountId, @NotNull Integer amount) {
        Account account = accountRepository.update(accountId, amount);
        boolean  result = accountEventBuss.push(account);
        return account;
    }

    public Account create() {
        Account account = accountRepository.create();
        boolean  result = accountEventBuss.push(account);
        return account;
    }

    public Long getBalance(String accountId) {
        return accountRepository.getBalance(accountId).orElse(null);
    }
}
