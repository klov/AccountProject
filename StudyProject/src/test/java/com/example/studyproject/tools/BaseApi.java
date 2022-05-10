package com.example.studyproject.tools;

import com.example.studyproject.repository.AccountRepository;
import com.example.studyproject.service.DbHandler;
import org.springframework.stereotype.Component;

@Component
public class BaseApi {

    private DbHandler dbHandler;

    public BaseApi(DbHandler dbHandler,  AccountRepository accountRepository) {
        this.dbHandler = dbHandler;
    }
    public void setup() {

    }

    public void deleteAll() {
        dbHandler.deleteAll();
    }
}
