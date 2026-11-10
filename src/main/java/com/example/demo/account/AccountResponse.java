package com.example.demo.account;

import java.math.BigDecimal;

public record AccountResponse(String accountNumber, BigDecimal balance) {
}
