package com.example.demo;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "balance", precision = 18, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    Account(User user, String accountNumber){
        this.accountNumber = accountNumber;
        this.user = user;
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
