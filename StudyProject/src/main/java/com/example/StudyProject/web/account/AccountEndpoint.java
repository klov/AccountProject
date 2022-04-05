package com.example.StudyProject.web.account;


import com.example.StudyProject.service.AccountService;
import com.example.StudyProject.service.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.constraints.NotNull;

@Validated
@Controller
@RequestMapping("/account")
public class AccountEndpoint {

    private final AccountService accountService;

    public AccountEndpoint(AccountService accountService) {
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
