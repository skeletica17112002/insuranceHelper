package com.example.insuranceAssist.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClaimCreateRequestDTO {

    private UUID clientId;
    private Long claimType;
    private String procedureNotes;
    private Long claimAmount;

}
