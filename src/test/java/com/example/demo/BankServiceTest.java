package com.example.demo;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.user.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankServiceTest {
    User aUser = new User("alogin", "123", "aname");
    Account aAccount = new Account(aUser, "anumber");

    User bUser = new User("blogin", "123", "bname");
    Account bAccount = new Account(bUser, "bnumber");

    AccountRepository accountRepository = mock(AccountRepository.class);
    BankService bankService = new BankService(accountRepository);

    @Test
    void shouldRejectTransferWhenInsufficientFunds() {
        aAccount.setBalance(BigDecimal.valueOf(80));
        bAccount.setBalance(BigDecimal.valueOf(100));

        when(accountRepository.findById("anumber")).thenReturn(Optional.of(aAccount));
        when(accountRepository.findById("bnumber")).thenReturn(Optional.of(bAccount));

        assertFalse(bankService.transfer("anumber", "bnumber", BigDecimal.valueOf(100)));
        assertEquals(BigDecimal.valueOf(80), aAccount.getBalance());
        assertEquals(BigDecimal.valueOf(100), bAccount.getBalance());
    }

    @Test
    void shouldAcceptTransferBetweenTwoAccounts() {
        aAccount.setBalance(BigDecimal.valueOf(50));
        bAccount.setBalance(BigDecimal.valueOf(80));

        when(accountRepository.findById("anumber")).thenReturn(Optional.of(aAccount));
        when(accountRepository.findById("bnumber")).thenReturn(Optional.of(bAccount));

        assertTrue(bankService.transfer("anumber", "bnumber", BigDecimal.valueOf(30)));
        assertEquals(BigDecimal.valueOf(20), aAccount.getBalance());
        assertEquals(BigDecimal.valueOf(110), bAccount.getBalance());
    }

    @Test
    void shouldRejectTransferWhenAmountIsNegative(){
        aAccount.setBalance(BigDecimal.valueOf(50));
        bAccount.setBalance(BigDecimal.valueOf(80));

        when(accountRepository.findById("anumber")).thenReturn(Optional.of(aAccount));
        when(accountRepository.findById("bnumber")).thenReturn(Optional.of(bAccount));

        assertFalse(bankService.transfer("anumber", "bnumber", new BigDecimal(-1)));

        assertEquals(new BigDecimal(50), aAccount.getBalance());
        assertEquals(new BigDecimal(80), bAccount.getBalance());
    }

    @Test
    void shouldRejectTransferToSameAccount() {
        aAccount.setBalance(BigDecimal.valueOf(50));

        when(accountRepository.findById("anumber")).thenReturn(Optional.of(aAccount));

        assertFalse(bankService.transfer("anumber", "anumber", new BigDecimal(20)));

        assertEquals(new BigDecimal(50), aAccount.getBalance());
    }

    @Test
    void shouldRejectTransferWhenReceiverDoesNotExist() {
        aAccount.setBalance(BigDecimal.valueOf(50));

        when(accountRepository.findById("anumber")).thenReturn(Optional.of(aAccount));
        when(accountRepository.findById("cnumber")).thenReturn(Optional.empty());

        assertFalse(bankService.transfer("anumber", "cnumber", new BigDecimal(20)));

        assertEquals(new BigDecimal(50), aAccount.getBalance());
    }

    @Test
    void shouldRejectTransferWhenAmountIsZero() {
        aAccount.setBalance(BigDecimal.valueOf(50));
        bAccount.setBalance(BigDecimal.valueOf(80));

        when(accountRepository.findById("anumber")).thenReturn(Optional.of(aAccount));
        when(accountRepository.findById("bnumber")).thenReturn(Optional.of(bAccount));

        assertFalse(bankService.transfer("anumber", "bnumber", new BigDecimal(0)));

        assertEquals(new BigDecimal(50), aAccount.getBalance());
        assertEquals(new BigDecimal(80), bAccount.getBalance());
    }

}
