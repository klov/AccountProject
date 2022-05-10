package com.example.studyproject.repository.buss;

import com.example.studyproject.repository.AccountEventBuss;
import com.example.studyproject.service.model.Account;
import org.springframework.stereotype.Component;

@Component
public class KafkaAccountEventBuss implements AccountEventBuss {
    @Override
    public boolean push(Account account) {
        return false;
    }
}
