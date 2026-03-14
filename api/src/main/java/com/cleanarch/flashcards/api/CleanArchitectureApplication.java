package com.cleanarch.flashcards.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.cleanarch.flashcards")
@EntityScan(basePackages = "com.cleanarch.flashcards.infrastructure.persistence.entities")
@EnableJpaRepositories(basePackages = "com.cleanarch.flashcards.infrastructure.persistence.repositories")
public class CleanArchitectureApplication {
    public static void main(String[] args) {
        SpringApplication.run(CleanArchitectureApplication.class, args);
    }
}
