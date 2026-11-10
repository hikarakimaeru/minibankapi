package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BankController {
    BankService bankService;

    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @PostMapping("user/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest transferRequest){
        if(bankService.transfer(transferRequest.accountNumber(), transferRequest.accountNumber2(), transferRequest.num())){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.badRequest().build();
    }
}
