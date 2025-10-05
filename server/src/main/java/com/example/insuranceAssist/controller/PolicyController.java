package com.example.insuranceAssist.controller;

import com.example.insuranceAssist.dto.PolicyCreateRequestDTO;
import com.example.insuranceAssist.dto.PolicyResponseDTO;
import com.example.insuranceAssist.dto.PolicyTypeResponseDTO;
import com.example.insuranceAssist.exception.ClientNotFoundException;
import com.example.insuranceAssist.exception.PolicyNotFoundException;
import com.example.insuranceAssist.exception.PolicyTypeNotFoundException;
import com.example.insuranceAssist.service.PolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/private/policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService){
        this.policyService = policyService;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/create")
    public ResponseEntity<UUID> createPolicy(@RequestBody PolicyCreateRequestDTO request)
            throws ClientNotFoundException, PolicyTypeNotFoundException {
        UUID policyId = policyService.createPolicy(request);
        return new ResponseEntity<>(policyId, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('CLIENT','AGENT')")
    @GetMapping("/get/{clientId}")
    public ResponseEntity<PolicyResponseDTO> getPolicy(@PathVariable UUID clientId) throws ClientNotFoundException {
        PolicyResponseDTO response = policyService.getPolicy(clientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT','AGENT')")
    @GetMapping("get/policyType")
    public ResponseEntity<?> getPolicyType() throws PolicyTypeNotFoundException {
        List<PolicyTypeResponseDTO> response = policyService.getPolicyType();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT','AGENT')")
    @GetMapping("/get/policyType/{clientId}")
    public ResponseEntity<PolicyTypeResponseDTO> getPolicyTypeByClient(@PathVariable UUID clientId)
            throws ClientNotFoundException {
        PolicyTypeResponseDTO response = policyService.getPolicyTypeByClient(clientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('AGENT')")
    @PutMapping("/update/{policyId}")
    public ResponseEntity<?> updatePolicy(@PathVariable UUID policyId, @RequestBody PolicyCreateRequestDTO request)
            throws PolicyNotFoundException, PolicyTypeNotFoundException {
        PolicyResponseDTO response = policyService.updatePolicy(policyId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('AGENT')")
    @DeleteMapping("/delete/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable UUID policyId){
        policyService.deletePolicy(policyId);
        return new ResponseEntity<>("Policy Deleted Successfully", HttpStatus.OK);
    }
}
