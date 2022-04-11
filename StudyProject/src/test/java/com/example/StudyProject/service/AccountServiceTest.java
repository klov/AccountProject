package com.example.StudyProject.service;

import com.example.StudyProject.tools.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest extends BaseTest {

    @Autowired
    AccountService

    @BeforeEach
    public void setup(){
        cleanDb();
    }


    @Test
    void fillAccount() {

    }

    @Test
    void create() {

    }

    @Test
    void getBalance() {
    }
}