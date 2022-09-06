package com.rdstew.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.rdstew.exceptions.InternalServerError;

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
        String redirect_uri = "http://localhost:8080/api/get-user-token";
        String response_type = "code";

        attributes.addAttribute("client_id", client_id);
        attributes.addAttribute("redirect_uri", redirect_uri);
        attributes.addAttribute("response_type", response_type);
        return new RedirectView("https://accounts.spotify.com/authorize");
    }

    @GetMapping("/api/get-user-token")
    public RedirectView getUserToken(RedirectAttributes attributes){
        
        return new RedirectView("http://localhost:8080/hello");
    }

    @GetMapping("/hello")
    public String getHello(){
        return("Hello");
    }


}
