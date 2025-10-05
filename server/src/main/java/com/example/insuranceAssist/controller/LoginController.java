package com.example.insuranceAssist.controller;

import com.example.insuranceAssist.dto.LoginRequestDTO;
import com.example.insuranceAssist.dto.LoginResponseDTO;
import com.example.insuranceAssist.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO response = loginService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
