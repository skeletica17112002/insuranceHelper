package com.example.insuranceAssist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClaimTypeNotFoundException extends Exception{

    public ClaimTypeNotFoundException(){
        super();
    }

    public ClaimTypeNotFoundException(String message){
        super(message);
    }

}
