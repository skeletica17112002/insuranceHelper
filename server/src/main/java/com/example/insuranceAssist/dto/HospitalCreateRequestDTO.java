package com.example.insuranceAssist.dto;

import lombok.Data;

@Data
public class HospitalCreateRequestDTO {

    private String name;
    private String address;
    private String email;
    private Double rating;
    private String clientContactEmail;
    private Long clientContactNumber;
    private int network;

}
