package com.example.insuranceAssist.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationRequestDTO {
    private String name;
    private String gender;
    private LocalDate dob;
    private Long phone;
    private String address;
    private String email;
    private String password;

}
