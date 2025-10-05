package com.example.insuranceAssist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends Exception {

    public ClientNotFoundException(){
        super();
    }

    public ClientNotFoundException(String message){
        super(message);
    }

}
