package com.example.zomind_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ZomindAssessmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZomindAssessmentApplication.class, args);
    }

}
