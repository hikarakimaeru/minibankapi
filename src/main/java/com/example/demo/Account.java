package com.example.demo;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "balance", precision = 18, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    Account(int userId, String accountNumber){
        this.accountNumber = accountNumber;
        this.userId = userId;
    }

    protected Account() {
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
