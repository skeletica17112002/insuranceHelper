package com.example.insuranceAssist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PolicyNotFoundException extends Exception {

    public PolicyNotFoundException(){super();}

    public PolicyNotFoundException(String message) {
        super(message);
    }
}
