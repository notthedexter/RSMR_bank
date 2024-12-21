package com.bracu.rsmr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RsmrApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RsmrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // No users will be created during startup
    }
}
