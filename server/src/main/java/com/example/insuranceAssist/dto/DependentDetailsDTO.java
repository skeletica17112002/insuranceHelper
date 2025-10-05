package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DependentDetailsDTO {

    private UUID id;
    private String name;
    private LocalDate dob;
    private String relation;
    private String gender;
    private String address;
    private String email;
    private Long phone;
}
