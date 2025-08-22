package com.bankingapp;

import com.bankingapp.model.User;
import com.bankingapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InternetBankingApplication {
    public static void main(String[] args) {
        SpringApplication.run(InternetBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner seed(UserRepository repo) {
        return args -> {
            if (repo.findByUsername("demo").isEmpty()) {
                repo.save(new User("demo", "demo@example.com", "plain-temp"));
                System.out.println("Inserted demo user");
            } else {
                System.out.println("Demo user already present");
            }
        };
    }
}
