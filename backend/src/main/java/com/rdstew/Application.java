package com.rdstew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan({"com.auth.controllers"})
public class Application{

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}