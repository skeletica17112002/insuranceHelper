package com.example.insuranceAssist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HospitalNotFoundException extends Exception {

    public HospitalNotFoundException(){}

    public HospitalNotFoundException(String message) {
        super(message);
    }
}
