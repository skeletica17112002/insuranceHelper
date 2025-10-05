package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DependentCreationRequestDTO {

    private String name;
    private LocalDate dob;
    private Long phone;
    private String address;
    private Long relationTypeId;
    private String gender;
    private String email;
    private UUID clientId;
}
