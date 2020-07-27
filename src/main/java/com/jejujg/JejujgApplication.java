package com.jejujg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan
public class JejujgApplication {

    public static void main(String[] args) {
        SpringApplication.run(JejujgApplication.class, args);
    }

}
