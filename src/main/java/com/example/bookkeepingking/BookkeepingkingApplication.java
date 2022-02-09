package com.example.bookkeepingking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BookkeepingkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookkeepingkingApplication.class, args);
    }
}
