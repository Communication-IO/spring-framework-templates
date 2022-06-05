package com.ahlquist.commio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveMongoTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoTemplateApplication.class, args);
    }

}
