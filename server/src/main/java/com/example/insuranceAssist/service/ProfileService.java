package com.example.insuranceAssist.service;

import com.example.insuranceAssist.dto.UserDetailsResponseDTO;
import com.example.insuranceAssist.dto.UserDetailsUpdateRequestDTO;
import com.example.insuranceAssist.entity.UserMaster;
import com.example.insuranceAssist.exception.UserNotFoundException;
import com.example.insuranceAssist.repository.UserMasterRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {

    private final UserMasterRepository userMasterRepository;

    public ProfileService(UserMasterRepository userMasterRepository) {
        this.userMasterRepository = userMasterRepository;
    }


    public UserDetailsResponseDTO getDetails(UUID userId) throws UserNotFoundException {

        UserMaster user = userMasterRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        return new UserDetailsResponseDTO(
                user.getUsername(),
                user.getName(),
                user.getGender(),
                user.getDob(),
                user.getAddress(),
                user.getEmail(),
                user.getPhone()
        );

    }

    public UserDetailsResponseDTO updateDetails(UUID userId, UserDetailsUpdateRequestDTO request) throws UserNotFoundException {
        UserMaster user = userMasterRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());

        userMasterRepository.save(user);

        return new UserDetailsResponseDTO(
                user.getUsername(),
                user.getName(),
                user.getGender(),
                user.getDob(),
                user.getAddress(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
