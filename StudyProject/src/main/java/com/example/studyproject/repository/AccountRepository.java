package com.example.studyproject.repository;

import com.example.studyproject.service.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account update(String accountId, Integer amount);

    Account create();

    Optional<Long> getBalance(String accountId);

    void deleteAll();

    Account create(UUID uuid, long value);
}
