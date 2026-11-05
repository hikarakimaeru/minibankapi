package com.example.demo;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance = BigDecimal.ZERO;
    private String accountNumber;
    private int userId;

    Account(int userId, String accountNumber){
        this.accountNumber = accountNumber;
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }
}
