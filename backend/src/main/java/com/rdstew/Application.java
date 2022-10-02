package com.rdstew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application{

    public static String backend_domain = "http://localhost:8080";
    public static String frontend_domain = "http://localhost:4200";

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}