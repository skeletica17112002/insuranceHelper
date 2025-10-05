package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsUpdateRequestDTO {

    private String address;
    private Long phone;
}
