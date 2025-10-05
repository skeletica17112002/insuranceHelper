package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDetailsResponseDTO {

        private String username;
        private String name;
        private String gender;
        private LocalDate dob;
        private String address;
        private String email;
        private Long phone;

}
