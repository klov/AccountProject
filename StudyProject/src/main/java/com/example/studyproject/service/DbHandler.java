package com.example.studyproject.service;

import com.example.studyproject.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class DbHandler {

    private final AccountRepository accountRepository;
    public DbHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }
}
