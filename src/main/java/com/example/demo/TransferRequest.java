package com.example.demo;

import java.math.BigDecimal;

public record TransferRequest(String accountNumber, String accountNumber2, BigDecimal num){
}
