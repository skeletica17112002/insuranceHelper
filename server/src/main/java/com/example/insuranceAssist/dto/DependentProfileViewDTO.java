package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DependentProfileViewDTO {

    private UUID id;
    private String name;
    private String gender;
    private String relationName;
    private LocalDate dob;
    private Long phone;
    private String email;
    private String address;

}
