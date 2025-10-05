package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HospitalDetailsResponseDTO {

    private String name;
    private String address;
    private String email;
    private Double rating;
    private String clientContactEmail;
    private Long clientContactNumber;
    private int network;

}
