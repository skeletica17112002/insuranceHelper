package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ClaimResponseDTO {

    private UUID claimId;
    private UUID policyId;
    private LocalDateTime openDate;
    private String procedureNotes;
    private String claimType;
    private String status;
    private Long claimAmount;
    private LocalDateTime updatedAt;

}
