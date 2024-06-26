package ru.Onshin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.Onshin")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}