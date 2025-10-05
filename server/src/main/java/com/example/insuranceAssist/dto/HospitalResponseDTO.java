package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class HospitalResponseDTO {

    private UUID id;
    private String name;
    private String address;
    private Double rating;
    private String clientContactEmail;
    private Long clientContactNumber;
    private int network;
}
