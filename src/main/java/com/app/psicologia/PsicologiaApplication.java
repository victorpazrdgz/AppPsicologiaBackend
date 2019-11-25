package com.app.psicologia;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@SpringBootApplication

public class PsicologiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsicologiaApplication.class, args);
    }

}
