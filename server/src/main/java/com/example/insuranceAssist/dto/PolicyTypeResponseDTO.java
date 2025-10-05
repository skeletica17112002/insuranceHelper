package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PolicyTypeResponseDTO {

    private Long id;
    private String tier;
    private int premiumBase;
    private int premiumPerDependent;
    private Long coverage;
    private int deductible;
    private int insurerPayPercentage;
    private String notes;

}
