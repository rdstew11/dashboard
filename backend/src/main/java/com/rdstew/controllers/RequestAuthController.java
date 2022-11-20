package com.rdstew.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;


import com.rdstew.exceptions.InternalServerError;
import com.rdstew.Application;
import com.rdstew.state.StateIdBuilder;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RequestAuthController {
    private String client_id;
    private String client_secret;
    private String access_token;
    private String redirect_uri  = Application.backend_domain + "/api/get-spotify-token";
    private Properties config;


    private static Logger logger = Logger.getLogger("com.rdstew");

    @GetMapping("/api/spotify/login")
    public RedirectView spotifyRedirectView( RedirectAttributes attributes){
        this.loadConfig();

        this.client_id = this.config.getProperty("client.id");
        String response_type = "code";

        String state_id = new StateIdBuilder()
            .length(16)
            .use_letters(true)
            .use_numbers(true)
            .buildStateId()
            .toString();
        System.out.println(state_id);
        attributes.addAttribute("client_id", this.client_id);
        attributes.addAttribute("redirect_uri", this.redirect_uri);
        attributes.addAttribute("response_type", response_type);
        attributes.addAttribute("state", state_id);
        return new RedirectView( Application.spotify_account_url + "/authorize");
    }

    @GetMapping("/api/get-spotify-token")
    public RedirectView getUserToken(
        @RequestParam("code") Optional<String> token, 
        @RequestParam("state") String state_id,
        @RequestParam("error") Optional<String> error
    ){
        if (token.isPresent()){
            String grant_type = "authorization_code";
            this.requestAuthToken(grant_type, token.get(), this.redirect_uri);
        } else {
            System.out.println(state_id + ' ' + error.get());
        }
        return new RedirectView(Application.frontend_domain + "/home");
    }

    @GetMapping("/hello")
    public String getHello(){
        System.out.print("/hello pinged");
        return("Hello");
    }

    @GetMapping("/id")
    public String getStateId(){
        return(new StateIdBuilder()
            .length(16)
            .use_letters(true)
            .use_numbers(true)
            .buildStateId()
            .toString()
        );
    }

    private void loadConfig(){
        this.config = new Properties();
        String fn = System.getenv("HOME") + "/SpotifyCredentials.conf";
        try(FileInputStream fis = new FileInputStream(fn)){
            this.config.load(fis);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            throw new InternalServerError();
        }

        this.client_id = this.config.getProperty("client.id");
        this.client_secret = this.config.getProperty("client.secret");
    }

    public int requestAuthToken(String grant_type, String token, String redirect_uri){
        RestTemplate http = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println(this.client_id);
        System.out.println(this.client_secret);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grant_type);
        body.add("code", token);
        body.add("redirect_uri", this.redirect_uri);
        body.add("client_id", this.client_id);
        body.add("client_secret", this.client_secret);
        System.out.println(body.toString());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
            http.exchange(
                Application.spotify_account_url + "/api/token",
                HttpMethod.POST,
                entity,
                String.class
            );
        System.out.println(response.getBody());

        return 0;
    }   
}
