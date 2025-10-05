package com.example.insuranceAssist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HospitalAlreadyExistsException extends Exception{

    public HospitalAlreadyExistsException(){
        super();
    }

    public HospitalAlreadyExistsException(String message){
        super(message);
    }
}
