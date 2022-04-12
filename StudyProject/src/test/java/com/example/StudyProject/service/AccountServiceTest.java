package com.example.StudyProject.service;

import com.example.StudyProject.service.model.Account;
import com.example.StudyProject.tools.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountServiceTest extends BaseTest {

    @Autowired
    AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        cleanDb();
    }


    @Test
    void fillAccount() {

    }

    @Test
    void create() throws Exception {
        String id = UUID.randomUUID().toString();
        mockMvc.perform(post("/account/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{id:%s}", id)))
                .andExpect(status().isOk())
                .andExpect(content().json(String.format("{id:%s, value:0}",id)));


    }

    @Test
    void getBalance() throws Exception {
        Account account = createAccount(UUID.randomUUID().toString(), 253);

        mockMvc.perform(post("/account/get"))
                .andExpect(status().isOk())
                .andExpect(content().string("253"));

    }
}