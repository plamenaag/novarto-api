package com.plamena.novartoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NovartoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovartoApiApplication.class, args);
    }
}
