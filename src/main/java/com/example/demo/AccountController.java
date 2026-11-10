package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountRequest accountRequest){
        Long userId = accountRequest.userId();

        if(accountRequest.userId() == null || userId <= 0){
            return ResponseEntity.badRequest().build();
        }

        accountService.createAccount(userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
