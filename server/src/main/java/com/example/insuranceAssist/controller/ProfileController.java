package com.example.insuranceAssist.controller;

import com.example.insuranceAssist.dto.UserDetailsResponseDTO;
import com.example.insuranceAssist.dto.UserDetailsUpdateRequestDTO;
import com.example.insuranceAssist.exception.UserNotFoundException;
import com.example.insuranceAssist.service.ProfileService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/private/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDetailsResponseDTO> getDetails(@PathVariable UUID userId) throws UserNotFoundException {
        UserDetailsResponseDTO response = profileService.getDetails(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDetailsResponseDTO> updateDetails(@PathVariable UUID userId, @RequestBody UserDetailsUpdateRequestDTO request) throws UserNotFoundException {
        UserDetailsResponseDTO response = profileService.updateDetails(userId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
