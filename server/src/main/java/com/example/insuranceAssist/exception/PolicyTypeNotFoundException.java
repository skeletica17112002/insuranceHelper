package com.example.insuranceAssist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PolicyTypeNotFoundException extends Exception {

    public PolicyTypeNotFoundException(){}

    public PolicyTypeNotFoundException(String message) {
        super(message);
    }
}
