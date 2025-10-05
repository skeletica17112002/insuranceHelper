package com.example.insuranceAssist.service;

import com.example.insuranceAssist.dto.LoginRequestDTO;
import com.example.insuranceAssist.dto.LoginResponseDTO;
import com.example.insuranceAssist.entity.UserMaster;
import com.example.insuranceAssist.repository.UserMasterRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {

    private final AuthenticationManager authManager;
    private final UserMasterRepository userMasterRepository;
    private final JwtService jwtService;

    public LoginService(AuthenticationManager authManager, UserMasterRepository userMasterRepository, JwtService jwtService){
        this.authManager = authManager;
        this.userMasterRepository = userMasterRepository;
        this.jwtService = jwtService;
    }

    public String verify(LoginRequestDTO request) {

        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(request.getUsername());
        }
        else{
            return "Failed";
        }
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        String token = verify(request);
        UserMaster user = userMasterRepository.findByUsername(request.getUsername());
        String username = user.getUsername();
        String role = user.getRole().getRoleName();
        UUID userId = user.getId();

        return new LoginResponseDTO(token, role, userId, username);

    }
}
