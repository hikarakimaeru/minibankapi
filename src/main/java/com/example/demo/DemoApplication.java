package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		User user = new User("hikarakimaeru", "123123", "Vladyslav", 0);
		User user2 = new User("bnt", "463562", "Ivan", 1);

		Account account = new Account(0, "LT1235");
		Account account2 = new Account(1, "UA1466");

		account.setBalance(BigDecimal.ONE);
		account2.setBalance(BigDecimal.TWO);

		System.out.println("First balance: " + account.getBalance());
		System.out.println("Second balance: " + account2.getBalance());

		BankService bankService = new BankService();

		bankService.addAccount(account);
		bankService.addAccount(account2);

		bankService.Transfer("LT1235", "UA1466", BigDecimal.ZERO);

		System.out.println("First balance afer: " + account.getBalance());
		System.out.println("Second balance after: " + account2.getBalance());

		//System.out.println(user.getLogin() + " " + user.getPassword() + " " + user.getName());
	}
}
