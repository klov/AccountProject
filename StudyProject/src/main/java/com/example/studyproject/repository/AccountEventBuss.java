package com.example.studyproject.repository;

import com.example.studyproject.service.model.Account;

public interface AccountEventBuss  {
    boolean push(Account account);
}
