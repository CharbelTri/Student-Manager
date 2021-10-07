package com.example.studentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@FlywayDataSource
@Configuration
@SpringBootApplication
@EnableSwagger2
public class StudentManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagerApplication.class, args);
    }
}