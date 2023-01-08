package com.rdstew.webgl.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShaderController{
    private static Logger logger = Logger.getLogger("com.rdstew");

    @GetMapping("/webgl/shaders")
    public ResponseEntity<byte[]> getShaderData(@RequestParam String type){
        System.out.println(System.getProperty("user.dir"));
        String path = null;
        byte[] shader_data = null;
        HttpStatus status = HttpStatus.OK;
        if (type.toLowerCase().equals("vertex")){
            path = "\\src\\main\\java\\com\\rdstew\\webgl\\shaders\\vert.shader";
        }
        else if (type.toLowerCase().equals("frag")){
            path = "\\src\\main\\java\\com\\rdstew\\webgl\\shaders\\frag.shader";
        }
        path = System.getProperty("user.dir") + path;
        logger.log(Level.FINE, "Pulling shader from: " + path);
        try {
            long file_size = new File(path).length();
            shader_data = new byte[(int) file_size];
            FileInputStream f_in = new FileInputStream(path);
            f_in.read(shader_data);
            f_in.close();
        }
        catch(IOException e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.log(Level.WARNING, String.format("Unable to retrieve data from: '%s'", path));
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
        return new ResponseEntity<byte[]>(shader_data, headers, status);
    }
}