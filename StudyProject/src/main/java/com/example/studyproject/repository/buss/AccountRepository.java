package com.example.studyproject.repository.buss;

import com.example.studyproject.service.model.Account;

public interface AccountRepository {
    Account update(String accountId, Integer amount);

    Account create();

    Integer getBalance(String accountId);

    void deleteAll();
}
