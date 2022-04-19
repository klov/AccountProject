package com.example.studyproject.web.account;


import com.example.studyproject.service.AccountService;
import com.example.studyproject.service.model.Account;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.constraints.NotNull;

@Validated
@Controller
@RequestMapping("/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("create")
    public Account createAccount(){
        return accountService.create();
    }

    @PostMapping("fill")
    public Account fillAccount(@RequestParam("accountId") @NotNull String accountId, @RequestParam("amount") @NotNull  Integer amount ){
        return accountService.fillAccount(accountId, amount);
    }

    @PostMapping("get")
    public Integer getBalance(@RequestParam("accountId") @NotNull String accountId){
        return accountService.getBalance(accountId);
    }

}
