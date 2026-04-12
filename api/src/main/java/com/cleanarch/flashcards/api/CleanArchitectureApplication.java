package com.cleanarch.flashcards.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.cleanarch.flashcards")
public class CleanArchitectureApplication {
    public static void main(String[] args) {
        SpringApplication.run(CleanArchitectureApplication.class, args);
    }
}
