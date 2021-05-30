package com.anil.issiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String SITE_IS_UP = "Site is up!!";
    private final String SITE_IS_DOWN = "Site is down!!";
    private final String ERROR_MESSAGE = "Incorrect URL!!";
    

    @GetMapping("/check")
    public String getUrlStatus(@RequestParam String url) {
        String message = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            
            if (responseCodeCategory != 2 && responseCodeCategory !=3) {
                message = SITE_IS_DOWN; 
               
            } else {
                message = SITE_IS_UP;
           }

        } catch (MalformedURLException e) {
            message = ERROR_MESSAGE;
        } catch (IOException e) {
            message = SITE_IS_DOWN;
        }
         
        return message;
    }
    
}
