package com.robotapocalypse.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestManagerImpl implements RequestManager{
    private final RestTemplate restTemplate = new RestTemplate();
    public ResponseEntity<String> getRequest (String url){
        return restTemplate.getForEntity(url, String.class);
    }
}
