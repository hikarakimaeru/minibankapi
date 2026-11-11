package com.example.demo;
import com.example.demo.account.*;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountServiceTest {
    AccountRepository accountRepository = mock(AccountRepository.class);
    UserRepository userRepository = mock(UserRepository.class);
    AccountService accountService = new AccountService(accountRepository, userRepository);

    User user = new User("hikara", "1235", "Vl");
    Account account = new Account(user, "213123");

    @Test
    void shouldReturnAccountsForUser() {
        long userId = 3;

        when(accountRepository.findByUser_Id(userId)).thenReturn(List.of(account));
        List<AccountResponse> accountResponses = accountService.findAllAccountsInfoByUserId(userId);

        assertEquals(1, accountResponses.size());
        assertEquals(BigDecimal.ZERO, accountResponses.getFirst().balance());
        assertEquals("213123", accountResponses.getFirst().accountNumber());
    }
}
