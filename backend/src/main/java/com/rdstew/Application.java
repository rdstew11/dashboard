package com.rdstew;

import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application{

    public static String backend_domain = "http://localhost:8080";
    public static String frontend_domain = "http://localhost:4200";
    public static String spotify_url = "https://api.spotify.com/v1";

    public static void main(String[] args){
        try{
            Handler log = new FileHandler("/home/rstew/Desktop/Dev/dashboard/backend/src/main/java/com/rdstew/logs/app.log");
            Logger.getLogger("com.rdstew").addHandler(log);
            Logger.getLogger("com.rdstew").setLevel(Level.FINEST);
        } catch (IOException e) {
            System.out.println("ERROR: Logging initialization failed.");
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        SpringApplication.run(Application.class, args);
    }
}