package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner testBank(BankService bankService){
		return args ->{
			User user = new User();
			User user2 = new User();

			Account account = new Account(user, "LT1235");
			Account account2 = new Account(user2, "UA1466");

			account.setBalance(BigDecimal.ONE);
			account2.setBalance(BigDecimal.TWO);

			System.out.println("Account 1 balance: " + account.getBalance());
			System.out.println("Account 2 balance: " + account2.getBalance());

			bankService.addAccount(account);
			bankService.addAccount(account2);

			System.out.println("Accounts created!");


			bankService.transfer(account.getAccountNumber(), account2.getAccountNumber(), BigDecimal.ONE);

			System.out.println("Account 1 balance after: " + bankService.getAccount("LT1235").getBalance());
			System.out.println("Account 2 balance after: " + bankService.getAccount("UA1466").getBalance());

			bankService.deposit("LT1235", BigDecimal.valueOf(15));

			System.out.println("Account 1 balance after deposit: " + bankService.getAccount("LT1235").getBalance());

			bankService.withdraw("LT1235", BigDecimal.valueOf(15));

			System.out.println("Account 1 balance after withdraw: " + bankService.getAccount("LT1235").getBalance());

		};
    }
}
