package com.rdstew.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import com.rdstew.exceptions.InternalServerError;
import com.rdstew.Application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RequestAuthController {
    private String client_id;
    private String client_secret;
    private String access_token;

    @GetMapping("/api/spotify-login")
    public RedirectView spotifyRedirectView( RedirectAttributes attributes){


        Properties spotifyConf = new Properties();
        String fn = "/home/ryan/Desktop/SpotifyCredentials.conf";
        try(FileInputStream fis = new FileInputStream(fn)){
            spotifyConf.load(fis);
        }
        catch(IOException ex){
            throw new InternalServerError();
        }

        client_id = spotifyConf.getProperty("client.id");
        client_secret = spotifyConf.getProperty("client.secret");
        String redirect_uri = Application.backend_domain + "/api/get-spotify-token";
        String response_type = "code";
        String state_id = this.generateStateId();

        attributes.addAttribute("client_id", client_id);
        attributes.addAttribute("redirect_uri", redirect_uri);
        attributes.addAttribute("response_type", response_type);
        attributes.addAttribute("state", state_id);
        return new RedirectView("https://accounts.spotify.com/authorize");
    }

    @GetMapping("/api/get-spotify-token")
    public RedirectView getUserToken(RedirectAttributes attributes){
        

        return new RedirectView(Application.frontend_domain + "/home");
    }

    @GetMapping("/hello")
    public String getHello(){
        return("Hello");
    }

    private String generateStateId(){
        
        int left_limit = 48;
        int right_limit = 122;
        int length = 16;
        Random random = new Random();
        String state_id = random.ints(left_limit, right_limit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i>= 97))
            .limit(length)
            .collecter(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
        return state_id;
    }


}
