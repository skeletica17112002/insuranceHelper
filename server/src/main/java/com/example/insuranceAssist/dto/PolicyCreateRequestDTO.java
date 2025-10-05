package com.example.insuranceAssist.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PolicyCreateRequestDTO {

    private UUID clientId;
    private Long policyType;
    private LocalDate startDate;
    private LocalDate endDate;
    private int noOfDependents;

}
