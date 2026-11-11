package com.example.demo.account;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    AccountRepository accountRepository;
    UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public void createAccount(long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User not found!"
        ));

        Account account = new Account(user, generateAccountNumber());

        accountRepository.save(account);
    }

    public List<AccountResponse> findAllAccountsInfoByUserId(long userId){
        List<Account> accounts = accountRepository.findByUser_Id(userId);
        List<AccountResponse> accountResponses = new ArrayList<>();

        for(Account account : accounts){
            accountResponses.add(new AccountResponse(account.getAccountNumber(), account.getBalance()));
        }

        return accountResponses;
    }

    public String generateAccountNumber(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
