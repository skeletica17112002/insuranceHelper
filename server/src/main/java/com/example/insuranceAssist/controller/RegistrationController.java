package com.example.insuranceAssist.controller;

import com.example.insuranceAssist.dto.RegistrationRequestDTO;
import com.example.insuranceAssist.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/public")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UUID> register(@RequestBody RegistrationRequestDTO request){
        UUID userId = registrationService.register(request);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

}
