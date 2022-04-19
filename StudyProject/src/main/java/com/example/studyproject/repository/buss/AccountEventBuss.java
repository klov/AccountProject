package com.example.studyproject.repository.buss;

import com.example.studyproject.service.model.Account;

public interface AccountEventBuss  {
    boolean push(Account account);
}
