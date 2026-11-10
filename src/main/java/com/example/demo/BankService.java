package com.example.demo;

import java.math.BigDecimal;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transfer(String accountNumber, String accountNumber2, BigDecimal num){
        if(num == null){
            System.out.println("Number can't be null");
            return;
        }
        if(accountNumber.equals(accountNumber2)){
            System.out.println("Account numbers are equal!");
            return;
        }

        Account account1 = findByAccountNumber(accountNumber);
        if(account1 == null){
            System.out.println("Wrong first account number");
            return;
        }
        Account account2 = findByAccountNumber(accountNumber2);
        if(account2 == null){
            System.out.println("Wrong second account number");
            return;
        }

        if(num.compareTo(BigDecimal.ZERO) <= 0 || account1.getBalance().compareTo(num) < 0){
            System.out.println("Error");
            return;
        }

        account1.setBalance(account1.getBalance().subtract(num));
        account2.setBalance(account2.getBalance().add(num));

        System.out.println("Transfer successful!");
    }

    @Transactional
    public void deposit(String accountNumber, BigDecimal num){
        if(num == null){
            System.out.println("Number can't be null");
            return;
        }

        Account account = findByAccountNumber(accountNumber);
        if(num.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("Error");
            return;
        }

        if(account == null){
            System.out.println("Account is not exist");
            return;
        }

        account.setBalance(account.getBalance().add(num));

        System.out.println("Deposit successful!");
    }

    @Transactional
    public void withdraw(String accountNumber, BigDecimal num){
        if(num == null){
            System.out.println("Number can't be null");
            return;
        }

        Account account = findByAccountNumber(accountNumber);
        if(num.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("Error");
            return;
        }

        if(account == null){
            System.out.println("Account is not exist");
            return;
        }

        if(account.getBalance().compareTo(num) < 0){
            System.out.println("Balance is too small!");
            return;
        }

        account.setBalance(account.getBalance().subtract(num));

        System.out.println("Withdraw successful!");
    }

    private Account findByAccountNumber(String accountNumber){
        return accountRepository.findById(accountNumber).orElse(null);
    }

    public void addAccount(Account account){
        accountRepository.save(account);
    }

    public Account getAccount(String accountNumber){
        return accountRepository.findById(accountNumber).orElseThrow();
    }
}
