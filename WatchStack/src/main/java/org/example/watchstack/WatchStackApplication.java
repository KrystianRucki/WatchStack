package org.example.watchstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("org.example.watchstack.repository")
@EntityScan("org.example.watchstack.entity")
public class WatchStackApplication {
    public static void main(String[] args) {
        SpringApplication.run(WatchStackApplication.class, args);
    }
}
