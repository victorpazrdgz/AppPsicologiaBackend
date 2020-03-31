package com.app.psicologia;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;


@SpringBootApplication

public class PsicologiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsicologiaApplication.class, args);
    }

}
