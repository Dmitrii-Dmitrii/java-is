package ru.Onshin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class, scanBasePackages = {"ru.Onshin"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}