package fr.ing.interview.bankaccountkata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class BankAccountKataApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountKataApplication.class, args);
	}

}
