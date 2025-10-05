package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    String token;
    String role;
    UUID userId;
    String username;
}
