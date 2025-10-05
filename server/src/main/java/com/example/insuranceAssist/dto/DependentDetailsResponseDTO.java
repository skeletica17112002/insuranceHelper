package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DependentDetailsResponseDTO {

    private UUID clientId;
    private List<DependentDetailsDTO> dependentDetails;
}
