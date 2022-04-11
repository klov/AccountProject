package com.example.StudyProject.repository.buss;

import com.example.StudyProject.service.model.Account;

public interface AccountEventBuss  {
    boolean push(Account account);
}
