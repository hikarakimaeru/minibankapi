package com.example.demo.account;


import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    AccountService accountService;

    public AccountController(AccountService accountService, UserRepository userRepository, AccountRepository accountRepository){
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountRequest accountRequest){
        long userId = accountRequest.userId();

        if(userId <= 0){
            return ResponseEntity.badRequest().build();
        }

        accountService.createAccount(userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user/{userId}/accounts")
    public List<AccountResponse> getAccounts(@PathVariable long userId){
        List<Account> accounts = accountRepository.findByUser_Id(userId);
        List<AccountResponse> accountResponses = new ArrayList<>();

        for (Account currAcc : accounts) {
            accountResponses.add(new AccountResponse(currAcc.getAccountNumber(), currAcc.getBalance()));
        }

        return accountResponses;
    }

}
