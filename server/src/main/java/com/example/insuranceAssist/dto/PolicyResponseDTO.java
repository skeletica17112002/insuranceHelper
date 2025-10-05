package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PolicyResponseDTO {

    private UUID policyId;
    private String tier;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long premium;
    private Long remainingCoverage;

}
