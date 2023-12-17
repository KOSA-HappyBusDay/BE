package com.example.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class APIResponse {
    public static ResponseEntity<?> success(Object data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    public static ResponseEntity<?> error(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
