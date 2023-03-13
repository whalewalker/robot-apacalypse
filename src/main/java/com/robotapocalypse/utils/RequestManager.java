package com.robotapocalypse.utils;

import org.springframework.http.ResponseEntity;

public interface RequestManager {
    ResponseEntity<String> getRequest (String url);
}
