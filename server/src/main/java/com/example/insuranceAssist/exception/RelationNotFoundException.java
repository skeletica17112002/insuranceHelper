package com.example.insuranceAssist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RelationNotFoundException extends Exception{

    public RelationNotFoundException(){}

    public RelationNotFoundException(String message){
        super(message);
    }

}
