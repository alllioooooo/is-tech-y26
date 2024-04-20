package com.alllioooooo.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.alllioooooo.repository")
@EntityScan("com.alllioooooo.entity")
@ComponentScan(basePackages = "com.alllioooooo")

public class Lab3Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab3Application.class, args);
//        var context = SpringApplication.run(Lab3Application.class, args);
//        CatController catController = context.getBean(CatController.class);
//        catController.getAllCats();
    }

}
