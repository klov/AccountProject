package com.example.StudyProject.repository.buss;

import com.example.StudyProject.service.model.Account;

public interface AccountRepository {
    Account update(String accountId, Integer amount);

    Account create();

    Integer getBalance(String accountId);
}
