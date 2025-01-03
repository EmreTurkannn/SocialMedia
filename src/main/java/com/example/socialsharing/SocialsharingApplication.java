package com.example.socialsharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.socialsharing.model")
public class SocialsharingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialsharingApplication.class, args);
    }
}
